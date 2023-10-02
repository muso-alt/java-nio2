package src.SynchronizedVector;

import java.util.Vector;

public class SynchronizedVector<E> extends Vector<E> {
    private final Vector<E> internalVector;

    public SynchronizedVector(Vector<E> internalVector) {
        this.internalVector = internalVector;
    }

    @Override
    public synchronized int size() {
        return internalVector.size();
    }

    @Override
    public synchronized boolean isEmpty() {
        return internalVector.isEmpty();
    }

    @Override
    public synchronized boolean contains(Object o) {
        return internalVector.contains(o);
    }

    // Другие методы интерфейса Vector, которые вы хотите использовать, также должны быть перегружены с synchronized.

    // Пример перегрузки метода add:
    @Override
    public synchronized boolean add(E e) {
        return internalVector.add(e);
    }

    // Пример перегрузки метода get:
    @Override
    public synchronized E get(int index) {
        return internalVector.get(index);
    }

    // Другие методы интерфейса Vector должны быть перегружены аналогично.

    // Остальные методы класса Object (equals, hashCode, toString и т. д.) могут быть оставлены по умолчанию.

    public static <E> Vector<E> synchronizedVector(Vector<E> vector) {
        return new SynchronizedVector<>(vector);
    }
}
