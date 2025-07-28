import java.time.LocalDate;

public class Hamster extends Pets {
    
    public Hamster(String name, LocalDate birthDate) {
        super(name, birthDate);
        // Добавляем базовые команды для хомяков
        addCommand("Roll");
        addCommand("Hide");
    }
    
    @Override
    public String getType() {
        return "Hamster";
    }
} 