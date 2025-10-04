package gb.jdk_5.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import gb.jdk_5.Model.Person;
import gb.jdk_5.View.GuiPersonView;
import javafx.application.Platform;

public class GuiPersonController {
    private Connection connection;
    private GuiPersonView view;
    private ExecutorService executorService;

    public GuiPersonController(GuiPersonView view) throws SQLException {
        this.view = view;
        this.executorService = Executors.newFixedThreadPool(4); // Пул потоков для работы с БД
        
        try {
            // Регистрируем драйвер PostgreSQL
            Class.forName("org.postgresql.Driver");
            
            String url = "jdbc:postgresql://localhost:5432/phonebook_database";
            String user = "postgres"; // Измените на вашего пользователя
            String password = "%h^Gl(gstj#d343Yhd6G"; // Измените на ваш пароль
            
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Подключение к базе данных установлено успешно!");
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL драйвер не найден: " + e.getMessage());
            throw new SQLException("Драйвер не найден", e);
        } catch (SQLException e) {
            System.err.println("Ошибка подключения к базе данных: " + e.getMessage());
            throw e;
        }
    }

    // Асинхронное добавление человека
    public CompletableFuture<Void> addPersonAsync(Person person) {
        return CompletableFuture.runAsync(() -> {
            String sql = "INSERT INTO humans (lastName, firstName, middleName, gender, birthdate, phonenumber) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, person.lastName);
                stmt.setString(2, person.firstName);
                stmt.setString(3, person.middleName);
                stmt.setString(4, person.gender);
                stmt.setString(5, person.birthdate);
                stmt.setString(6, person.phonenumber);
                stmt.executeUpdate();
                
                Platform.runLater(() -> {
                    view.showSuccess("Человек успешно добавлен в базу данных.");
                    view.clearForm();
                });
            } catch (SQLException e) {
                Platform.runLater(() -> {
                    view.showError("Ошибка при добавлении в БД: " + e.getMessage());
                });
            }
        }, executorService);
    }

    // Асинхронное получение списка всех людей
    public CompletableFuture<List<Person>> getAllPeopleAsync() {
        return CompletableFuture.supplyAsync(() -> {
            List<Person> people = new ArrayList<>();
            String sql = "SELECT lastName, firstName, middleName, gender, birthdate, phonenumber FROM humans ORDER BY lastName, firstName";
            
            try (PreparedStatement stmt = connection.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                
                while (rs.next()) {
                    Person person = new Person(
                        rs.getString("lastName"),
                        rs.getString("firstName"),
                        rs.getString("middleName"),
                        rs.getString("gender"),
                        rs.getString("birthdate"),
                        rs.getString("phonenumber")
                    );
                    people.add(person);
                }
            } catch (SQLException e) {
                Platform.runLater(() -> {
                    view.showError("Ошибка при получении списка: " + e.getMessage());
                });
            }
            
            return people;
        }, executorService);
    }

    // Асинхронный поиск по фамилии
    public CompletableFuture<List<Person>> searchByLastNameAsync(String lastName) {
        return CompletableFuture.supplyAsync(() -> {
            List<Person> people = new ArrayList<>();
            String sql = "SELECT lastName, firstName, middleName, gender, birthdate, phonenumber FROM humans WHERE lastName ILIKE ? ORDER BY lastName, firstName";
            
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, "%" + lastName + "%");
                ResultSet rs = stmt.executeQuery();
                
                while (rs.next()) {
                    Person person = new Person(
                        rs.getString("lastName"),
                        rs.getString("firstName"),
                        rs.getString("middleName"),
                        rs.getString("gender"),
                        rs.getString("birthdate"),
                        rs.getString("phonenumber")
                    );
                    people.add(person);
                }
            } catch (SQLException e) {
                Platform.runLater(() -> {
                    view.showError("Ошибка при поиске: " + e.getMessage());
                });
            }
            
            return people;
        }, executorService);
    }

    // Асинхронное удаление человека
    public CompletableFuture<Void> deletePersonAsync(String lastName, String firstName, String phoneNumber) {
        return CompletableFuture.runAsync(() -> {
            String sql = "DELETE FROM humans WHERE lastName = ? AND firstName = ? AND phonenumber = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, lastName);
                stmt.setString(2, firstName);
                stmt.setString(3, phoneNumber);
                int affectedRows = stmt.executeUpdate();
                
                Platform.runLater(() -> {
                    if (affectedRows > 0) {
                        view.showSuccess("Человек успешно удален.");
                        view.refreshPersonList();
                    } else {
                        view.showError("Человек не найден для удаления.");
                    }
                });
            } catch (SQLException e) {
                Platform.runLater(() -> {
                    view.showError("Ошибка при удалении: " + e.getMessage());
                });
            }
        }, executorService);
    }

    // Закрытие соединения и пула потоков
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Соединение с базой данных закрыто.");
            }
        } catch (SQLException e) { 
            System.err.println("Ошибка при закрытии соединения: " + e.getMessage());
        }
        
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdown();
        }
    }
} 