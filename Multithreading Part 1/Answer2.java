class IncreDecres {
    int vari;

    IncreDecres() {
        vari = 0;
    }

    synchronized void increaser() {
        vari++;
    }

    void decreaser() {
       synchronized (this){
           vari--;
       }
    }

    void show() {
        System.out.println(vari);
    }
}

class ExerciseTTN extends Thread {
    IncreDecres count;
    ExerciseTTN(IncreDecres count) {
        this.count = count;
    }

    public void run() {
        for (int i = 0; i < 10000; i++)
            count.increaser();
    }
}

public class Answer2 {
    public static void main(String[] args) throws InterruptedException {
        IncreDecres count = new IncreDecres();

        ExerciseTTN t1 = new ExerciseTTN(count);
        ExerciseTTN t2 = new ExerciseTTN(count);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        count.show();


    }
}
