package src;

import java.util.Random;
import java.util.Vector;

class WriterThread extends Thread {
    private Vector<Double> vector;

    public WriterThread(Vector<Double> vector) {
        this.vector = vector;
    }

    public void run() {
        Random random = new Random();

        System.out.println(vector.capacity());
        for (int i = 0; i < 10; i++) {
            double value = random.nextDouble() * 100; // Генерируем случайное значение от 0 до 100
            vector.add(value);
            
            System.out.println("Write: " + value + " to position " + i);
            try {
                Thread.sleep(100); // Приостанавливаем нить на короткое время
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ReaderThread extends Thread {
    private Vector<Double> vector;

    public ReaderThread(Vector<Double> vector) {
        this.vector = vector;
    }

    public void run() {
        for (int i = 0; i < vector.size(); i++) {
            double value = vector.get(i);
            System.out.println("Read: " + value + " from position " + i);
            try {
                Thread.sleep(100); // Приостанавливаем нить на короткое время
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class VectorThreadExample {
    public static void main(String[] args) {
        Vector<Double> sharedVector = new Vector<>(10);

        // Заполняющая нить
        WriterThread writerThread = new WriterThread(sharedVector);

        // Считывающая нить
        ReaderThread readerThread = new ReaderThread(sharedVector);

        // Запуск нитей
        writerThread.run();
        readerThread.run();
    }
}