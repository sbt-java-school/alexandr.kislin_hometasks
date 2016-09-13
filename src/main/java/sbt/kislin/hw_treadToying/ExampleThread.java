package sbt.kislin.hw_treadToying;

/**
 * Created by axel on 09.09.2016.
 */
public class ExampleThread implements Runnable {
    private Thread thrd;


    public ExampleThread(String threadName) {
        thrd = new Thread(threadName);
        thrd.start();
    }

    @Override
    public void run() {
        System.out.println(thrd.getName()+" запускается...");
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(400);
                System.out.println("В "+thrd.getName()+" счетчик равен "+i);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(thrd.getName()+" прерван");
            }
            System.out.println("Завершение потока "+thrd.getName());
        }
    }
}
