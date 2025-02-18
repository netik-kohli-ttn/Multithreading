import java.util.concurrent.*;

class Task implements Runnable {
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running.");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Exception: " + e);
        }
    }
}

public class Answer3 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService single = Executors.newSingleThreadExecutor();
        ExecutorService cached = Executors.newCachedThreadPool();
        ExecutorService fixed = Executors.newFixedThreadPool(3);

        Runnable task = new Task();

        for (int i = 0; i < 3; i++) {
            single.submit(task);
            cached.submit(task);
            fixed.submit(task);
        }

        single.shutdown();
        cached.shutdown();
        fixed.shutdown();

        single.awaitTermination(10, TimeUnit.SECONDS);
        cached.awaitTermination(10, TimeUnit.SECONDS);
        fixed.awaitTermination(10, TimeUnit.SECONDS);
    }
}
