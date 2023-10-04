package src;

class IncrementThread extends Thread {
    private int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 100; j++) {
            i++;
            System.out.println("Thread " + getId() + ": i = " + i);
        }
    }
}

public class IncrementThreadMain {
    public static void main(String[] args) {
        IncrementThread thread1 = new IncrementThread();
        IncrementThread thread2 = new IncrementThread();

        thread1.start();
        thread2.start();
    }
}
