/**
 * Утилитарный класс с обобщёнными методами для работы с парами
 */
public class PairUtils {

    /**
     * Обобщённый метод для сложения числовых пар
     * 
     * @param pair пара с числовыми значениями
     * @return сумма элементов пары
     */
    public static <T extends Number> double sumPair(Pair<T, T> pair) {
        return pair.getFirst().doubleValue() + pair.getSecond().doubleValue();
    }

    /**
     * Обобщённый метод для конкатенации строковых пар
     * 
     * @param pair пара со строками
     * @return конкатенация строк
     */
    public static String concatenatePair(Pair<String, String> pair) {
        return pair.getFirst() + pair.getSecond();
    }

    /**
     * Обобщённый метод для создания новой пары с поменянными местами элементами
     * 
     * @param pair исходная пара
     * @return новая пара с поменянными элементами
     */
    public static <T, U> Pair<U, T> swapPair(Pair<T, U> pair) {
        return new Pair<>(pair.getSecond(), pair.getFirst());
    }

    /**
     * Обобщённый метод для проверки, равны ли элементы пары
     * 
     * @param pair пара для проверки
     * @return true если элементы равны, false иначе
     */
    public static <T> boolean areEqual(Pair<T, T> pair) {
        if (pair.getFirst() == null && pair.getSecond() == null) {
            return true;
        }
        if (pair.getFirst() == null || pair.getSecond() == null) {
            return false;
        }
        return pair.getFirst().equals(pair.getSecond());
    }

    /**
     * Обобщённый метод для нахождения максимального элемента в паре
     * 
     * @param pair пара с Comparable элементами
     * @return максимальный элемент
     */
    public static <T extends Comparable<T>> T getMax(Pair<T, T> pair) {
        return pair.getFirst().compareTo(pair.getSecond()) >= 0 ? pair.getFirst() : pair.getSecond();
    }

    /**
     * Обобщённый метод для нахождения минимального элемента в паре
     * 
     * @param pair пара с Comparable элементами
     * @return минимальный элемент
     */
    public static <T extends Comparable<T>> T getMin(Pair<T, T> pair) {
        return pair.getFirst().compareTo(pair.getSecond()) <= 0 ? pair.getFirst() : pair.getSecond();
    }

    /**
     * Обобщённый метод для преобразования пары в строку с разделителем
     * 
     * @param pair      пара для преобразования
     * @param separator разделитель между элементами
     * @return строка с элементами пары
     */
    public static <T, U> String toStringWithSeparator(Pair<T, U> pair, String separator) {
        return pair.getFirst() + separator + pair.getSecond();
    }
}