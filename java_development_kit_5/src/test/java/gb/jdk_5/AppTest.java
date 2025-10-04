package gb.jdk_5;

import gb.jdk_5.Model.Person;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    
    @Test
    public void testPersonCreation() {
        Person person = new Person("Иванов", "Иван", "Иванович", "male", "1990-01-01", "1234567890");
        
        assertEquals("Иванов", person.getLastName());
        assertEquals("Иван", person.getFirstName());
        assertEquals("Иванович", person.getMiddleName());
        assertEquals("male", person.getGender());
        assertEquals("1990-01-01", person.getBirthdate());
        assertEquals("1234567890", person.getPhonenumber());
    }
    
    @Test
    public void testPersonSetters() {
        Person person = new Person("", "", "", "", "", "");
        
        person.setLastName("Петров");
        person.setFirstName("Петр");
        person.setMiddleName("Петрович");
        person.setGender("male");
        person.setBirthdate("1985-05-15");
        person.setPhonenumber("9876543210");
        
        assertEquals("Петров", person.getLastName());
        assertEquals("Петр", person.getFirstName());
        assertEquals("Петрович", person.getMiddleName());
        assertEquals("male", person.getGender());
        assertEquals("1985-05-15", person.getBirthdate());
        assertEquals("9876543210", person.getPhonenumber());
    }
}
