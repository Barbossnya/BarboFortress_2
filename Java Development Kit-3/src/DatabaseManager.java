import java.util.HashMap;
import java.util.Map;

public class DatabaseManager implements DatabaseOperations {
    // "Эмуляция" базы данных в виде HashMap
    private Map<Integer, Object> zoo_database = new HashMap<>();

    // Метод для сохранения объекта
    @Override
    public void save(Object obj) {
        int id = zoo_database.size() + 1; // ID генерируется автоматически (счетчик)
        zoo_database.put(id, obj);
        System.out.println("Сохранено: " + obj + " с ID = " + id);
    }

    // Метод для удаления объекта по ID
    @Override
    public void delete(int id) {
        if (zoo_database.containsKey(id)) {
            Object removed = zoo_database.remove(id);
            System.out.println("Удалено: " + removed + " с ID = " + id);
        } else {
            System.out.println("Объект с ID = " + id + " не найден.");
        }
    }

    // Метод для получения объекта по ID
    @Override
    public Object getById(int id) {
        if (zoo_database.containsKey(id)) {
            return zoo_database.get(id);
        } else {
            System.out.println("Объект с ID = " + id + " не найден.");
            return null;
        }
    }

    // Метод для обновления объекта
    @Override
    public void update(int id, Object newData) {
        if (zoo_database.containsKey(id)) {
            zoo_database.put(id, newData);
            System.out.println("Объект с ID = " + id + " обновлен. Новые данные: " + newData);
        } else {
            System.out.println("Объект с ID = " + id + " не найден.");
        }
    }
}