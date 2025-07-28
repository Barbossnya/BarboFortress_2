import java.time.LocalDate;

public class Camel extends PackAnimals {
    
    public Camel(String name, LocalDate birthDate) {
        super(name, birthDate);
        // Добавляем базовые команды для верблюдов
        addCommand("Walk");
        addCommand("Carry Load");
    }
    
    @Override
    public String getType() {
        return "Camel";
    }
} 