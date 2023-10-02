package src;

class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Поток, созданный с помощью реализации Runnable: " + i);
            try {
                Thread.sleep(1000); // Приостанавливаем выполнение потока на 1 секунду
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class RunnableImplementationExample {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable); // Создаем объект Thread, передавая экземпляр MyRunnable
        thread.start(); // Запускаем поток

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