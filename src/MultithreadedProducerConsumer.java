package src;

import java.util.Random;
import java.util.concurrent.locks.*;

class CircularBuffer {
    private final int[] buffer;
    private final int bufferSize;
    private int count;
    private int writeIndex;
    private int readIndex;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public CircularBuffer(int size) {
        this.bufferSize = size;
        this.buffer = new int[size];
        this.count = 0;
        this.writeIndex = 0;
        this.readIndex = 0;
    }

    public void produce(int value) throws InterruptedException {
        lock.lock();
        try {
            while (count == bufferSize) {
                notFull.await();
            }
            buffer[writeIndex] = value;
            writeIndex = (writeIndex + 1) % bufferSize;
            count++;
            System.out.println("Produced: " + value);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public int consume() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            int value = buffer[readIndex];
            readIndex = (readIndex + 1) % bufferSize;
            count--;
            System.out.println("Consumed: " + value);
            notFull.signal();
            return value;
        } finally {
            lock.unlock();
        }
    }
}

class Producer implements Runnable {
    private final CircularBuffer buffer;
    private final int id;
    private final Random random = new Random();

    public Producer(CircularBuffer buffer, int id) {
        this.buffer = buffer;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int value = random.nextInt(100); // Генерируем случайное значение
                buffer.produce(value);
                Thread.sleep(random.nextInt(100)); // Спим случайное время
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Consumer implements Runnable {
    private final CircularBuffer buffer;
    private final int id;
    private final Random random = new Random();

    public Consumer(CircularBuffer buffer, int id) {
        this.buffer = buffer;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int value = buffer.consume();
                Thread.sleep(random.nextInt(1000)); // Спим случайное время
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class MultithreadedProducerConsumer {
    public static void main(String[] args) {
        int bufferSize = 10;
        CircularBuffer buffer = new CircularBuffer(bufferSize);

        int numProducers = 3; // Количество производителей
        int numConsumers = 5; // Количество потребителей

        Thread[] producerThreads = new Thread[numProducers];
        Thread[] consumerThreads = new Thread[numConsumers];

        // Создаем и запускаем потоки производителей
        for (int i = 0; i < numProducers; i++) {
            producerThreads[i] = new Thread(new Producer(buffer, i));
            producerThreads[i].start();
        }

        // Создаем и запускаем потоки потребителей
        for (int i = 0; i < numConsumers; i++) {
            consumerThreads[i] = new Thread(new Consumer(buffer, i));
            consumerThreads[i].start();
        }
    }
}
