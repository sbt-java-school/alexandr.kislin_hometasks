package sbt.kislin.hw6_CachedProxy;

/**
 * Created by Александр on 01.09.2016.
 */
public class CalculatorImpl implements Calculator {
    @Override
    public int sum(int x, int y) {
        return x+y;
    }

    @Override
    public int multiple(int x, int y) {
        return x*y;
    }
}
