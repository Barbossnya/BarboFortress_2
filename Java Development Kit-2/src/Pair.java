/**
 * Обобщённый класс Pair для хранения пары значений
 * 
 * @param <T> тип первого элемента
 * @param <U> тип второго элемента
 */
public class Pair<T, U> {
    private T first;
    private U second;

    /**
     * Конструктор для создания пары
     * 
     * @param first  первый элемент
     * @param second второй элемент
     */
    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Получить первый элемент
     * 
     * @return первый элемент
     */
    public T getFirst() {
        return first;
    }

    /**
     * Получить второй элемент
     * 
     * @return второй элемент
     */
    public U getSecond() {
        return second;
    }

    /**
     * Установить первый элемент
     * 
     * @param first новый первый элемент
     */
    public void setFirst(T first) {
        this.first = first;
    }

    /**
     * Установить второй элемент
     * 
     * @param second новый второй элемент
     */
    public void setSecond(U second) {
        this.second = second;
    }

    /**
     * Создать пару с двумя элементами
     * 
     * @param first  первый элемент
     * @param second второй элемент
     * @return новая пара
     */
    public static <T, U> Pair<T, U> of(T first, U second) {
        return new Pair<>(first, second);
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Pair<?, ?> pair = (Pair<?, ?>) obj;
        return (first == null ? pair.first == null : first.equals(pair.first)) &&
                (second == null ? pair.second == null : second.equals(pair.second));
    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (second != null ? second.hashCode() : 0);
        return result;
    }
}