import java.time.LocalDate;

public class Dog extends Pets {
    
    public Dog(String name, LocalDate birthDate) {
        super(name, birthDate);
        // Добавляем базовые команды для собак
        addCommand("Sit");
        addCommand("Stay");
        addCommand("Fetch");
    }
    
    @Override
    public String getType() {
        return "Dog";
    }
} 