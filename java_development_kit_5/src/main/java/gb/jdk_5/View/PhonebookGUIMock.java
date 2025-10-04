package gb.jdk_5.View;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import gb.jdk_5.Controller.MockGuiPersonController;
import gb.jdk_5.Model.Person;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class PhonebookGUIMock extends Application implements GuiPersonView {
    private MockGuiPersonController controller;
    
    // UI компоненты
    private TextField lastNameField;
    private TextField firstNameField;
    private TextField middleNameField;
    private DatePicker birthDatePicker;
    private TextField phoneField;
    private ComboBox<String> genderComboBox;
    private TextArea resultArea;
    private ListView<String> personListView;
    private TextField searchField;
    private Button addButton;
    private Button searchButton;
    private Button refreshButton;
    private Button deleteButton;
    private ProgressIndicator loadingIndicator;
    
    @Override
    public void start(Stage primaryStage) {
        try {
            controller = new MockGuiPersonController(this);
            initializeUI(primaryStage);
            loadPersonList();
        } catch (Exception e) {
            showError("Ошибка инициализации: " + e.getMessage());
        }
    }
    
    private void initializeUI(Stage primaryStage) {
        primaryStage.setTitle("Телефонный справочник (Mock версия)");
        
        // Создание компонентов формы
        lastNameField = new TextField();
        lastNameField.setPromptText("Фамилия");
        
        firstNameField = new TextField();
        firstNameField.setPromptText("Имя");
        
        middleNameField = new TextField();
        middleNameField.setPromptText("Отчество");
        
        birthDatePicker = new DatePicker();
        birthDatePicker.setPromptText("Дата рождения");
        
        phoneField = new TextField();
        phoneField.setPromptText("Номер телефона");
        
        genderComboBox = new ComboBox<>();
        genderComboBox.getItems().addAll("male", "female");
        genderComboBox.setPromptText("Пол");
        
        // Кнопки
        addButton = new Button("Добавить");
        addButton.setOnAction(e -> addPerson());
        
        searchField = new TextField();
        searchField.setPromptText("Поиск по фамилии");
        
        searchButton = new Button("Поиск");
        searchButton.setOnAction(e -> searchPeople());
        
        refreshButton = new Button("Обновить список");
        refreshButton.setOnAction(e -> loadPersonList());
        
        deleteButton = new Button("Удалить выбранного");
        deleteButton.setOnAction(e -> deleteSelectedPerson());
        
        // Область результатов
        resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.setPrefRowCount(3);
        
        // Список людей
        personListView = new ListView<>();
        personListView.setPrefHeight(200);
        
        // Индикатор загрузки
        loadingIndicator = new ProgressIndicator();
        loadingIndicator.setVisible(false);
        
        // Создание layout
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        
        // Форма добавления
        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(5);
        
        formGrid.add(new Label("Фамилия:"), 0, 0);
        formGrid.add(lastNameField, 1, 0);
        formGrid.add(new Label("Имя:"), 0, 1);
        formGrid.add(firstNameField, 1, 1);
        formGrid.add(new Label("Отчество:"), 0, 2);
        formGrid.add(middleNameField, 1, 2);
        formGrid.add(new Label("Дата рождения:"), 0, 3);
        formGrid.add(birthDatePicker, 1, 3);
        formGrid.add(new Label("Телефон:"), 0, 4);
        formGrid.add(phoneField, 1, 4);
        formGrid.add(new Label("Пол:"), 0, 5);
        formGrid.add(genderComboBox, 1, 5);
        
        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(addButton, loadingIndicator);
        buttonBox.setAlignment(Pos.CENTER_LEFT);
        
        // Поиск
        HBox searchBox = new HBox(10);
        searchBox.getChildren().addAll(searchField, searchButton, refreshButton);
        searchBox.setAlignment(Pos.CENTER_LEFT);
        
        // Удаление
        HBox deleteBox = new HBox(10);
        deleteBox.getChildren().addAll(deleteButton);
        deleteBox.setAlignment(Pos.CENTER_LEFT);
        
        // Сборка интерфейса
        root.getChildren().addAll(
            new Label("Добавить нового человека:"),
            formGrid,
            buttonBox,
            new Separator(),
            new Label("Поиск:"),
            searchBox,
            new Separator(),
            new Label("Список людей:"),
            personListView,
            deleteBox,
            new Separator(),
            new Label("Результаты операций:"),
            resultArea
        );
        
        Scene scene = new Scene(root, 600, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // Обработчик закрытия окна
        primaryStage.setOnCloseRequest(e -> {
            if (controller != null) {
                controller.close();
            }
        });
    }
    
    private void addPerson() {
        if (!validateForm()) {
            return;
        }
        
        LocalDate birthDate = birthDatePicker.getValue();
        if (birthDate == null) {
            showError("Выберите дату рождения");
            return;
        }
        
        Person person = new Person(
            lastNameField.getText().trim(),
            firstNameField.getText().trim(),
            middleNameField.getText().trim(),
            genderComboBox.getValue(),
            birthDate.format(DateTimeFormatter.ISO_LOCAL_DATE),
            phoneField.getText().trim()
        );
        
        showLoading(true);
        controller.addPersonAsync(person).thenRun(() -> {
            Platform.runLater(() -> {
                showLoading(false);
                loadPersonList();
            });
        });
    }
    
    private boolean validateForm() {
        if (lastNameField.getText().trim().isEmpty()) {
            showError("Введите фамилию");
            return false;
        }
        if (firstNameField.getText().trim().isEmpty()) {
            showError("Введите имя");
            return false;
        }
        if (phoneField.getText().trim().isEmpty()) {
            showError("Введите номер телефона");
            return false;
        }
        if (genderComboBox.getValue() == null) {
            showError("Выберите пол");
            return false;
        }
        return true;
    }
    
    private void searchPeople() {
        String searchText = searchField.getText().trim();
        if (searchText.isEmpty()) {
            loadPersonList();
            return;
        }
        
        showLoading(true);
        controller.searchByLastNameAsync(searchText).thenAccept(people -> {
            Platform.runLater(() -> {
                showLoading(false);
                updatePersonList(people);
            });
        });
    }
    
    private void loadPersonList() {
        showLoading(true);
        controller.getAllPeopleAsync().thenAccept(people -> {
            Platform.runLater(() -> {
                showLoading(false);
                updatePersonList(people);
            });
        });
    }
    
    private void deleteSelectedPerson() {
        String selected = personListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Выберите человека для удаления");
            return;
        }
        
        // Парсим строку для получения данных
        String[] parts = selected.split(" - ");
        if (parts.length < 3) {
            showError("Неверный формат данных");
            return;
        }
        
        String lastName = parts[0];
        String firstName = parts[1];
        String phoneNumber = parts[2];
        
        showLoading(true);
        controller.deletePersonAsync(lastName, firstName, phoneNumber).thenRun(() -> {
            Platform.runLater(() -> {
                showLoading(false);
            });
        });
    }
    
    @Override
    public void showSuccess(String message) {
        resultArea.appendText("✓ " + message + "\n");
    }
    
    @Override
    public void showError(String message) {
        resultArea.appendText("✗ " + message + "\n");
    }
    
    @Override
    public void clearForm() {
        lastNameField.clear();
        firstNameField.clear();
        middleNameField.clear();
        birthDatePicker.setValue(null);
        phoneField.clear();
        genderComboBox.setValue(null);
    }
    
    @Override
    public void refreshPersonList() {
        loadPersonList();
    }
    
    @Override
    public void updatePersonList(List<Person> people) {
        personListView.getItems().clear();
        for (Person person : people) {
            String displayText = String.format("%s - %s - %s", 
                person.lastName, person.firstName, person.phonenumber);
            personListView.getItems().add(displayText);
        }
    }
    
    @Override
    public void showLoading(boolean isLoading) {
        loadingIndicator.setVisible(isLoading);
        addButton.setDisable(isLoading);
        searchButton.setDisable(isLoading);
        refreshButton.setDisable(isLoading);
        deleteButton.setDisable(isLoading);
    }
} 