package sbt.kislin.hw6_CachedProxy;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Александр on 01.09.2016.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Cached {
    long value();
}
