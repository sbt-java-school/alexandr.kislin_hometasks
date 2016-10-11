package sbt.kislin.hw6_CachedProxy;

/**
 * Created by Александр on 01.09.2016.
 */
@Cached(10L)
public interface Calculator {
    int sum(int x, int y);
    int multiple (int x, int y);
}
