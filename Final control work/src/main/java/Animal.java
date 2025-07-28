import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Animal {
    private static int counter = 0;
    private int id;
    private String name;
    private LocalDate birthDate;
    private List<String> commands;
    
    public Animal(String name, LocalDate birthDate) {
        this.id = ++counter;
        this.name = name;
        this.birthDate = birthDate;
        this.commands = new ArrayList<>();
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public LocalDate getBirthDate() {
        return birthDate;
    }
    
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    
    public List<String> getCommands() {
        return new ArrayList<>(commands);
    }
    
    public void addCommand(String command) {
        if (!commands.contains(command)) {
            commands.add(command);
        }
    }
    
    public void removeCommand(String command) {
        commands.remove(command);
    }
    
    public int getAgeInMonths() {
        LocalDate now = LocalDate.now();
        return (int) ((now.getYear() - birthDate.getYear()) * 12 + 
                      (now.getMonthValue() - birthDate.getMonthValue()));
    }
    
    public abstract String getType();
    
    public static int getTotalCount() {
        return counter;
    }
    
    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, Type: %s, BirthDate: %s, Commands: %s", 
                           id, name, getType(), birthDate, String.join(", ", commands));
    }
} 