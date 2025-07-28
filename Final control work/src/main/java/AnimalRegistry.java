import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class AnimalRegistry {
    private List<Animal> animals;
    private Scanner scanner;
    private DateTimeFormatter dateFormatter;
    
    public AnimalRegistry() {
        this.animals = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }
    
    public void addAnimal() {
        System.out.println("\n=== Добавление нового животного ===");
        System.out.println("Выберите тип животного:");
        System.out.println("1. Собака");
        System.out.println("2. Кошка");
        System.out.println("3. Хомяк");
        System.out.println("4. Лошадь");
        System.out.println("5. Верблюд");
        System.out.println("6. Осел");
        System.out.print("Введите номер (1-6): ");
        
        int choice = getIntInput();
        if (choice < 1 || choice > 6) {
            System.out.println("Неверный выбор!");
            return;
        }
        
        System.out.print("Введите имя животного: ");
        String name = scanner.nextLine();
        
        System.out.print("Введите дату рождения (формат: yyyy-MM-dd): ");
        String birthDateStr = scanner.nextLine();
        
        LocalDate birthDate;
        try {
            birthDate = LocalDate.parse(birthDateStr, dateFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Неверный формат даты! Используйте формат yyyy-MM-dd");
            return;
        }
        
        Animal animal = createAnimal(choice, name, birthDate);
        if (animal != null) {
            animals.add(animal);
            System.out.println("Животное успешно добавлено!");
            System.out.println("Общее количество животных: " + Animal.getTotalCount());
        }
    }
    
    private Animal createAnimal(int choice, String name, LocalDate birthDate) {
        switch (choice) {
            case 1: return new Dog(name, birthDate);
            case 2: return new Cat(name, birthDate);
            case 3: return new Hamster(name, birthDate);
            case 4: return new Horse(name, birthDate);
            case 5: return new Camel(name, birthDate);
            case 6: return new Donkey(name, birthDate);
            default: return null;
        }
    }
    
    public void listCommands() {
        System.out.println("\n=== Список команд животного ===");
        if (animals.isEmpty()) {
            System.out.println("Нет животных в реестре!");
            return;
        }
        
        System.out.println("Выберите животное:");
        for (int i = 0; i < animals.size(); i++) {
            System.out.printf("%d. %s (%s)\n", i + 1, animals.get(i).getName(), animals.get(i).getType());
        }
        
        int choice = getIntInput();
        if (choice < 1 || choice > animals.size()) {
            System.out.println("Неверный выбор!");
            return;
        }
        
        Animal animal = animals.get(choice - 1);
        System.out.println("Команды для " + animal.getName() + " (" + animal.getType() + "):");
        List<String> commands = animal.getCommands();
        if (commands.isEmpty()) {
            System.out.println("Нет команд");
        } else {
            for (String command : commands) {
                System.out.println("- " + command);
            }
        }
    }
    
    public void teachCommand() {
        System.out.println("\n=== Обучение новой команде ===");
        if (animals.isEmpty()) {
            System.out.println("Нет животных в реестре!");
            return;
        }
        
        System.out.println("Выберите животное:");
        for (int i = 0; i < animals.size(); i++) {
            System.out.printf("%d. %s (%s)\n", i + 1, animals.get(i).getName(), animals.get(i).getType());
        }
        
        int choice = getIntInput();
        if (choice < 1 || choice > animals.size()) {
            System.out.println("Неверный выбор!");
            return;
        }
        
        Animal animal = animals.get(choice - 1);
        System.out.print("Введите новую команду: ");
        String command = scanner.nextLine();
        
        animal.addCommand(command);
        System.out.println("Команда '" + command + "' добавлена для " + animal.getName());
    }
    
    public void listByBirthDate() {
        System.out.println("\n=== Список животных по дате рождения ===");
        if (animals.isEmpty()) {
            System.out.println("Нет животных в реестре!");
            return;
        }
        
        List<Animal> sortedAnimals = new ArrayList<>(animals);
        sortedAnimals.sort(Comparator.comparing(Animal::getBirthDate));
        
        for (Animal animal : sortedAnimals) {
            System.out.printf("%s (%s) - %s\n", 
                animal.getName(), 
                animal.getType(), 
                animal.getBirthDate().format(dateFormatter));
        }
    }
    
    public void showTotalCount() {
        System.out.println("\n=== Общее количество животных ===");
        System.out.println("Всего животных: " + Animal.getTotalCount());
        
        long petsCount = animals.stream().filter(a -> a instanceof Pets).count();
        long packAnimalsCount = animals.stream().filter(a -> a instanceof PackAnimals).count();
        
        System.out.println("Домашних животных: " + petsCount);
        System.out.println("Вьючных животных: " + packAnimalsCount);
    }
    
    public void showMenu() {
        while (true) {
            System.out.println("\n=== РЕЕСТР ДОМАШНИХ ЖИВОТНЫХ ===");
            System.out.println("1. Добавить новое животное");
            System.out.println("2. Список команд животного");
            System.out.println("3. Обучить новой команде");
            System.out.println("4. Список животных по дате рождения");
            System.out.println("5. Показать общее количество животных");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    addAnimal();
                    break;
                case 2:
                    listCommands();
                    break;
                case 3:
                    teachCommand();
                    break;
                case 4:
                    listByBirthDate();
                    break;
                case 5:
                    showTotalCount();
                    break;
                case 0:
                    System.out.println("До свидания!");
                    return;
                default:
                    System.out.println("Неверный выбор!");
            }
        }
    }
    
    private int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Введите число: ");
            }
        }
    }
    
    public void close() {
        scanner.close();
    }
} 