import java.time.LocalDate;

public class Cat extends Pets {
    
    public Cat(String name, LocalDate birthDate) {
        super(name, birthDate);
        // Добавляем базовые команды для кошек
        addCommand("Sit");
        addCommand("Pounce");
    }
    
    @Override
    public String getType() {
        return "Cat";
    }
} 