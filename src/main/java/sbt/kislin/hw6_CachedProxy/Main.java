package sbt.kislin.hw6_CachedProxy;

import java.lang.reflect.Proxy;

/**
 * Created by Александр on 01.09.2016.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Calculator calcImpl = new CalculatorImpl();
        Object instance = Proxy.newProxyInstance(Main.class.getClassLoader(),
                new Class<?>[]{Calculator.class},
                new CachedInvocationsHandler(calcImpl));
        Calculator cachCalc = (Calculator) instance;
        System.out.println(cachCalc.sum(1,5));
        System.out.println(cachCalc.sum(1,5));

        Thread.sleep(10);
        System.out.println("Plus after sleep");
        System.out.println(cachCalc.sum(1, 5));
        System.out.println(cachCalc.sum(1, 5));

        System.out.println("Multiples:");
        System.out.println(cachCalc.multiple(2, 2));
        System.out.println(cachCalc.multiple(2, 2));
        System.out.println(cachCalc.multiple(4, 1));

        Thread.sleep(10);
        System.out.println("Multiple after sleep");
        System.out.println(cachCalc.multiple(4, 1));
        System.out.println(cachCalc.multiple(2, 2));
        System.out.println(cachCalc.multiple(2, 2));
    }
}
