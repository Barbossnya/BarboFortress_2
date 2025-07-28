public class Main {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в систему учета питомника!");
        System.out.println("Система учета домашних и вьючных животных");
        
        AnimalRegistry registry = new AnimalRegistry();
        
        try {
            registry.showMenu();
        } finally {
            registry.close();
        }
    }
} 