import java.time.LocalDate;

public abstract class Pets extends Animal {
    
    public Pets(String name, LocalDate birthDate) {
        super(name, birthDate);
    }
    
    @Override
    public String getType() {
        return "Pet";
    }
} 