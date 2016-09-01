package sbt.kislin.hw6_CachedProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by Александр on 01.09.2016.
 */
public class CachedInvocationsHandler<T> implements InvocationHandler {

    private Calculator instance;
    private HashMap<String, DataUtils<T>> cacheMap = new HashMap<>();

    public CachedInvocationsHandler(Calculator instance) {
        this.instance = instance;
    }

    @Override
    public T invoke(Object proxy, Method method, Object[] args) throws Throwable {
        T value = null;
        Long valueAnnotation = getAnnotationValue(method);
        if (valueAnnotation != 0) {
            String tempKey = createKeyForCache(method, args);
            if (cacheMap.containsKey(tempKey)) {
                if (cacheMap.get(tempKey).shouldUseCache()) {
                    System.out.println("We should use cache for it operation, coz we have their result in cache");
                    return cacheMap.get(tempKey).getValue();
                } else {
                    System.out.println("we have key of it operation in cache, but we need update value");
                    value = (T) method.invoke(instance, args);
                    cacheMap.put(tempKey, new DataUtils<T>(System.currentTimeMillis()+valueAnnotation,value));
                }
            } else {
                System.out.println("we havent result of it operation in cache, doing it and add result in cache");
                value = (T) method.invoke(instance, args);
                cacheMap.put(tempKey, new DataUtils<T>(System.currentTimeMillis() + valueAnnotation, value));
            }
        } else {
            //метод без аннотации, и не пытаемся ничего кешировать
            value = (T) method.invoke(instance, args);
        }
        return value;
    }

    private long getAnnotationValue(Method method) {
        Cached annotation = method.getAnnotation(Cached.class);
        if (annotation == null &&
                (annotation = method.getDeclaringClass().getAnnotation(Cached.class)) == null &&
                (annotation = instance.getClass().getAnnotation(Cached.class)) == null) {
            return 0;
        } else {
            return annotation.value();
        }
    }

    private String createKeyForCache(Method method, Object[] args) {
        StringBuilder tmp = new StringBuilder();
        tmp.append(method.getDeclaringClass().getSimpleName());
        if (args != null && args.length > 0) {
            for (Object arg : args) {
                if (arg == null) {
                    throw new IllegalArgumentException("Havent args?");
                }
                tmp.append("_").append(arg.toString());
            }
        }
        return tmp.toString();
    }
}
