/* Урок 2. Программные интерфейсы
Реализуйте простой обобщённый класс, такой как пара или кортеж. 
Затем создайте обобщенный метод, который работает с этим классом. 
Например, вы можете создать метод, который принимает пару и возвращает их сумму или конкатенацию.
 */

/**
 * Главный класс для демонстрации работы с обобщёнными классами и методами
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Демонстрация работы с обобщёнными классами и методами ===\n");

        // Демонстрация работы с числовыми парами
        System.out.println("1. Работа с числовыми парами:");
        Pair<Integer, Integer> intPair = new Pair<>(10, 20);
        Pair<Double, Double> doublePair = new Pair<>(3.14, 2.71);

        System.out.println("Пара целых чисел: " + intPair);
        System.out.println("Сумма элементов: " + PairUtils.sumPair(intPair));

        System.out.println("Пара чисел с плавающей точкой: " + doublePair);
        System.out.println("Сумма элементов: " + PairUtils.sumPair(doublePair));

        // Демонстрация работы со строковыми парами
        System.out.println("\n2. Работа со строковыми парами:");
        Pair<String, String> stringPair = new Pair<>("Hello, ", "World!");
        System.out.println("Строковая пара: " + stringPair);
        System.out.println("Конкатенация: " + PairUtils.concatenatePair(stringPair));

        // Демонстрация обмена элементов
        System.out.println("\n3. Обмен элементов пары:");
        Pair<String, Integer> mixedPair = new Pair<>("Возраст", 25);
        System.out.println("Исходная пара: " + mixedPair);
        Pair<Integer, String> swappedPair = PairUtils.swapPair(mixedPair);
        System.out.println("Пара после обмена: " + swappedPair);

        // Демонстрация сравнения элементов
        System.out.println("\n4. Сравнение элементов:");
        Pair<String, String> equalPair = new Pair<>("Java", "Java");
        Pair<String, String> differentPair = new Pair<>("Java", "Python");

        System.out.println("Пара с равными элементами: " + equalPair);
        System.out.println("Элементы равны: " + PairUtils.areEqual(equalPair));

        System.out.println("Пара с разными элементами: " + differentPair);
        System.out.println("Элементы равны: " + PairUtils.areEqual(differentPair));

        // Демонстрация поиска максимума и минимума
        System.out.println("\n5. Поиск максимума и минимума:");
        Pair<Integer, Integer> numbersPair = new Pair<>(42, 17);
        System.out.println("Пара чисел: " + numbersPair);
        System.out.println("Максимум: " + PairUtils.getMax(numbersPair));
        System.out.println("Минимум: " + PairUtils.getMin(numbersPair));

        // Демонстрация преобразования в строку с разделителем
        System.out.println("\n6. Преобразование в строку с разделителем:");
        Pair<String, String> namePair = new Pair<>("Иван", "Петров");
        System.out.println("Пара имён: " + namePair);
        System.out.println("С разделителем ' ': " + PairUtils.toStringWithSeparator(namePair, " "));
        System.out.println("С разделителем ' - ': " + PairUtils.toStringWithSeparator(namePair, " - "));

        // Демонстрация статического метода создания пары
        System.out.println("\n7. Использование статического метода создания:");
        Pair<Character, Character> charPair = Pair.of('A', 'Z');
        System.out.println("Пара символов: " + charPair);

        // Демонстрация работы с null значениями
        System.out.println("\n8. Работа с null значениями:");
        Pair<String, String> nullPair = new Pair<>(null, null);
        System.out.println("Пара с null: " + nullPair);
        System.out.println("Элементы равны: " + PairUtils.areEqual(nullPair));

        System.out.println("\n=== Демонстрация завершена ===");
    }
}