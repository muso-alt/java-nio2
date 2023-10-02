package src;

class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Поток, созданный с помощью расширения Thread: " + i);
            try {
                Thread.sleep(1000); // Приостанавливаем выполнение потока на 1 секунду
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ThreadExtensionExample {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start(); // Запускаем поток

        for (int i = 0; i < 5; i++) {
            System.out.println("Главный поток: " + i);
            try {
                Thread.sleep(1000); // Приостанавливаем выполнение главного потока на 1 секунду
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}