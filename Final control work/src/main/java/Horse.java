import java.time.LocalDate;

public class Horse extends PackAnimals {
    
    public Horse(String name, LocalDate birthDate) {
        super(name, birthDate);
        // Добавляем базовые команды для лошадей
        addCommand("Trot");
        addCommand("Canter");
        addCommand("Gallop");
    }
    
    @Override
    public String getType() {
        return "Horse";
    }
} 