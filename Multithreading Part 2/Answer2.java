import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Exercise2 {
    private final Lock lock1 = new ReentrantLock();
    private final Lock lock2 = new ReentrantLock();

    public void method() {
        lock1.lock();
        try {
            System.out.println(Thread.currentThread().getName() + ": Holding lock 1");
            Thread.sleep(1000);
            lock2.lock();
            try {
                System.out.println(Thread.currentThread().getName() + ": Holding lock 2");
                Thread.sleep(1000);
            } finally {
                lock2.unlock();
                System.out.println(Thread.currentThread().getName() + ": Releasing lock 2");
            }
        } catch (InterruptedException e) {
            System.out.println("Exception : " + e);
        } finally {
            lock1.unlock();
            System.out.println(Thread.currentThread().getName() + ": Release lock 1 \n");
        }
    }
}

class ResolveDeadlock implements Runnable {
    Exercise2 obj;
    ResolveDeadlock(Exercise2 obj) {
        this.obj = obj;
    }
    public void run() {
        obj.method();
    }
}
    public class Answer2{
    public static void main(String[] args) {
        Exercise2 example = new Exercise2();
        ResolveDeadlock resolve1 = new ResolveDeadlock(example);
        ResolveDeadlock resolve2 = new ResolveDeadlock(example);
        Thread t1 = new Thread(resolve1);
        Thread t2 = new Thread(resolve2);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Exception : " + e);
        }
    }
}