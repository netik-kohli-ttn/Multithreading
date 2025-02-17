class CommonRes {
    String name;

    CommonRes(String name) {
        this.name = name;
    }
}

class Deadlock extends Thread {
    private final CommonRes r1;
    private final CommonRes r2;

    Deadlock(CommonRes r1, CommonRes r2) {
        this.r1 = r1;
        this.r2 = r2;
    }

    public void run() {
        synchronized (r1) {
            System.out.println(getName() + " locked " + r1.name);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Interrupt: "+ e);
            }
            synchronized (r2) {
                System.out.println(getName() + " locked " + r2.name);
            }
        }
    }
}

public class Answer4 {
    public static void main(String[] args) {
        CommonRes r1 = new CommonRes("Resource 1");
        CommonRes r2 = new CommonRes("Resource 2");

        Thread t1 = new Deadlock(r1, r2);
        Thread t2 = new Deadlock(r2, r1);

        t1.start();
        t2.start();
    }
}
