package src;

import java.util.concurrent.Semaphore;

class SharedResource {
    private double value;
    private Semaphore writeSemaphore = new Semaphore(1); // Мьютекс для записи
    private Semaphore readSemaphore = new Semaphore(0); // Мьютекс для чтения

    public void write(double newValue) throws InterruptedException {
        writeSemaphore.acquire();
        this.value = newValue;
        System.out.println("Write: " + newValue);
        readSemaphore.release();
    }

    public double read() throws InterruptedException {
        readSemaphore.acquire();
        double readValue = this.value;
        System.out.println("Read: " + readValue);
        writeSemaphore.release();
        return readValue;
    }
}

class WriterThread implements Runnable {
    private SharedResource sharedResource;

    public WriterThread(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                double newValue = Math.random() * 100;
                sharedResource.write(newValue);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ReaderThread implements Runnable {
    private SharedResource sharedResource;

    public ReaderThread(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                sharedResource.read();
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class SequentialReadWriteExample {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        Thread writerThread = new Thread(new WriterThread(sharedResource));
        Thread readerThread = new Thread(new ReaderThread(sharedResource));

        writerThread.start();
        readerThread.start();
    }
}