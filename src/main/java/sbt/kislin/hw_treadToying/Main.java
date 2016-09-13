package sbt.kislin.hw_treadToying;

/**
 * Created by axel on 09.09.2016.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Запуск Main потока");

        ExampleThread thread = new ExampleThread("Custom thread");
        thread.run();
        for (int i = 0; i < 50; i++) {
            System.out.println(".");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("Прерывание основного потока");
            }
            System.out.println("Завершение основного потока");
        }
    }
}
