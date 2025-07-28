import java.time.LocalDate;

public class Donkey extends PackAnimals {
    
    public Donkey(String name, LocalDate birthDate) {
        super(name, birthDate);
        // Добавляем базовые команды для ослов
        addCommand("Walk");
        addCommand("Carry Load");
        addCommand("Bray");
    }
    
    @Override
    public String getType() {
        return "Donkey";
    }
} 