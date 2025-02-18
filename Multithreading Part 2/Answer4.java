import java.util.concurrent.*;

class RandomNumber implements Callable<Integer> {
    public Integer call() {
        int randomNumber = (int) (Math.random() * 100);
        System.out.println("Generated Random Number: " + randomNumber);
        return randomNumber;
    }
}

public class Answer4 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<Integer> task = new RandomNumber();

        Future<Integer> future = executor.submit(task);
        try {
            System.out.println("Random Value: " + future.get());
        } catch (ExecutionException e) {
            System.out.println("Exception caught: " + e);
        }

        executor.shutdown();
    }
}
