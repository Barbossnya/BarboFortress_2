package gb.jdk_5.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import gb.jdk_5.Model.Person;
import gb.jdk_5.View.GuiPersonView;
import javafx.application.Platform;

public class MockGuiPersonController {
    private GuiPersonView view;
    private ExecutorService executorService;
    private List<Person> people; // Хранение данных в памяти

    public MockGuiPersonController(GuiPersonView view) {
        this.view = view;
        this.executorService = Executors.newFixedThreadPool(4);
        this.people = new ArrayList<>();
        
        // Добавляем тестовые данные
        initializeTestData();
        System.out.println("Mock контроллер инициализирован с тестовыми данными!");
    }

    private void initializeTestData() {
        people.add(new Person("Столотов", "Иван", "Викторович", "male", "1991-03-21", "742344446244"));
        people.add(new Person("Лебедева", "Мария", "Сергеевна", "female", "1988-08-12", "84964646244"));
        people.add(new Person("Бакуленко", "Алиса", "Алексеевна", "female", "1999-01-18", "79216165144"));
        people.add(new Person("Разумовский", "Михаил", "Семенович", "male", "1979-11-09", "89498946244"));
        people.add(new Person("Лоскутов", "Пётр", "Ильич", "male", "2000-05-05", "71234567890"));
    }

    // Асинхронное добавление человека
    public CompletableFuture<Void> addPersonAsync(Person person) {
        return CompletableFuture.runAsync(() -> {
            // Имитируем задержку сети
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            // Проверяем, не существует ли уже такой человек
            boolean exists = people.stream()
                .anyMatch(p -> p.lastName.equals(person.lastName) && 
                             p.firstName.equals(person.firstName) && 
                             p.phonenumber.equals(person.phonenumber));
            
            if (exists) {
                Platform.runLater(() -> {
                    view.showError("Человек с такими данными уже существует!");
                });
                return;
            }
            
            people.add(person);
            
            Platform.runLater(() -> {
                view.showSuccess("Человек успешно добавлен в память.");
                view.clearForm();
            });
        }, executorService);
    }

    // Асинхронное получение списка всех людей
    public CompletableFuture<List<Person>> getAllPeopleAsync() {
        return CompletableFuture.supplyAsync(() -> {
            // Имитируем задержку сети
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            return new ArrayList<>(people);
        }, executorService);
    }

    // Асинхронный поиск по фамилии
    public CompletableFuture<List<Person>> searchByLastNameAsync(String lastName) {
        return CompletableFuture.supplyAsync(() -> {
            // Имитируем задержку сети
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            List<Person> results = new ArrayList<>();
            String searchTerm = lastName.toLowerCase();
            
            for (Person person : people) {
                if (person.lastName.toLowerCase().contains(searchTerm)) {
                    results.add(person);
                }
            }
            
            return results;
        }, executorService);
    }

    // Асинхронное удаление человека
    public CompletableFuture<Void> deletePersonAsync(String lastName, String firstName, String phoneNumber) {
        return CompletableFuture.runAsync(() -> {
            // Имитируем задержку сети
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            boolean removed = people.removeIf(p -> 
                p.lastName.equals(lastName) && 
                p.firstName.equals(firstName) && 
                p.phonenumber.equals(phoneNumber)
            );
            
            Platform.runLater(() -> {
                if (removed) {
                    view.showSuccess("Человек успешно удален из памяти.");
                    view.refreshPersonList();
                } else {
                    view.showError("Человек не найден для удаления.");
                }
            });
        }, executorService);
    }

    // Закрытие пула потоков
    public void close() {
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdown();
        }
        System.out.println("Mock контроллер закрыт.");
    }
} 