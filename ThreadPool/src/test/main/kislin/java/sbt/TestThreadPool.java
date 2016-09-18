package main.kislin.java.sbt;

import org.junit.Test;
import sbt.kislin.ThreadPool;

import java.util.concurrent.TimeUnit;

/**
 * Created by axel on 18.09.2016.
 */
public class TestThreadPool {
    @Test
    public void testThreadPool(){
        ThreadPool pool = new ThreadPool(5);

        for (int i = 0; i < 12; i++) {
            String threadName = "This is task #"+i;
            pool.addTask(() -> {
                try{
                    System.out.println(threadName);
                    TimeUnit.SECONDS.sleep(2);
                }catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            });
        }
    }

    @Test
    public void exceptionThreadPool(){
        ThreadPool pool = new ThreadPool(5);

        for (int i = 0; i < 12; i++) {
            String threadName = "This is task #"+i;
            pool.addTask(() -> {
                try{
                    System.out.println(threadName);
                    TimeUnit.SECONDS.sleep(2);
                    throw new RuntimeException("Exception");
                }catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            });
        }
    }
}
