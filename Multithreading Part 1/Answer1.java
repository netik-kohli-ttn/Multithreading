class UseRunnable implements Runnable {
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                System.out.println("Runnable: " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("UseRunnable: " + e);
        }
    }
}

class UseThread extends Thread {
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                System.out.println("Thread: " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("UseThread: " + e);
        }
    }
}

public class Answer1 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new UseRunnable());
        UseThread thread2 = new UseThread();

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}