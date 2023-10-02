package src.SynchronizedVector;

import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        Vector<Integer> originalVector = new Vector<>();
        Vector<Integer> synchronizedVector = SynchronizedVector.synchronizedVector(originalVector);

        // Теперь synchronizedVector можно использовать в многопоточном окружении безопасно.

        // Пример добавления элемента в синхронизированный вектор:
        synchronizedVector.add(42);

        // Пример получения элемента из синхронизированного вектора:
        int value = synchronizedVector.get(0);

        System.out.println(value);
    }
}