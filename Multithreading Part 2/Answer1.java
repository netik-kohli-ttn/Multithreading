import java.util.concurrent.*;

class MyRunnable implements Runnable {
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Runnable");
    }
}

class MyCallable implements Callable<String> {
    public String call() throws InterruptedException {
        Thread.sleep(2000);
        return "Callable";
    }
}

public class Answer1 {
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newFixedThreadPool(2);

        exec.execute(new MyRunnable());
        Future<String> future = exec.submit(new MyCallable());
        System.out.println(future.get());
        exec.shutdown();
    }
}
