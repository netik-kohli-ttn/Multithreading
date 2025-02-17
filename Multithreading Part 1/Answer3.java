import java.util.Scanner;

public class Answer3 implements Runnable {
    volatile boolean isContinue = true;

    public void run() {
        while (isContinue) {
            System.out.println("JVM Competency");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("Exception : " + e);
            }
        }

    }

    public void stop(){
        isContinue = false;
    }

    public static void main(String[] args) {
        Answer3 obj = new Answer3();
        Thread t = new Thread(obj);
        t.start();

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        if (str.equals("exit"))
            obj.stop();

        sc.close();
    }
}