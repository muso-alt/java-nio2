package src;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizationExample {
    private static int count = 0;
    private static final int TARGET_COUNT = 10;
    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            try {
                lock.lock();
                while (count < TARGET_COUNT) {
                    System.out.println("Thread A: Waiting...");
                    condition.await();
                }
                System.out.println("Thread A: Condition met. Exiting.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                lock.lock();
                while (count < TARGET_COUNT) {
                    System.out.println("Thread B: Incrementing count...");
                    count++;
                    if (count == TARGET_COUNT) {
                        System.out.println("Thread B: Signaling Thread A...");
                        condition.signal();
                    }
                }
            } finally {
                lock.unlock();
            }
        });

        threadA.start();
        threadB.start();
    }
}
