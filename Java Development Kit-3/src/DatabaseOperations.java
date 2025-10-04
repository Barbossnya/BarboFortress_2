public interface DatabaseOperations {
    void save(Object obj); // Метод для сохранения объекта в базу данных
    void delete(int id);  // Метод для удаления объекта по ID
    Object getById(int id); // Метод для получения объекта по ID
    void update(int id, Object newData); // Метод для обновления объекта
}
