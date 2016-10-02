package sbt.kislin.hw6_CachedProxy;

/**
 * Created by Александр on 01.09.2016.
 */
public class DataUtils<T> {
    private long time;
    private T value;

    public DataUtils(long time, T value) {
        this.time = time;
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public boolean shouldUseCache() {
        return time > System.currentTimeMillis();
    }
}
