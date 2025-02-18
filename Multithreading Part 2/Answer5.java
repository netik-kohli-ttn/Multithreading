import java.util.concurrent.*;

class Exercise implements Runnable {
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ": is running");
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + ": was interrupted");
        }
    }
}

public class Answer5 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        Exercise compare = new Exercise();
        Thread task = new Thread(compare);

        for (int i = 0; i < 3; i++) {
            executor.submit(task);
        }

        Thread.sleep(2000);
        System.out.println("Calling shutdown()...");
        executor.shutdown();
        if (!executor.awaitTermination(3, TimeUnit.SECONDS)) {
            System.out.println("Calling shutdownNow()...");
            executor.shutdownNow();
        }
    }
}
