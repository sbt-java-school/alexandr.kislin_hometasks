package sbt.kislin.hw5_reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by Александр on 01.09.2016.
 */
public class BeanUtils {

    public static void main(String[] args) throws Exception {
        ClassForBeanTo to = new ClassForBeanTo("test class name");
        Main.Test from = new Main.Test("name");
        assign(to, from);
    }

    public static void assign(Object to, Object from) throws Exception {
        Method[] methodsOfFromClass = from.getClass().getMethods();
        Method[] methodsOfToClass = to.getClass().getMethods();
        Field[] fieldsOfToClass = to.getClass().getDeclaredFields();
        if (methodsOfFromClass == null || fieldsOfToClass == null || methodsOfToClass == null) {
            throw new IllegalArgumentException("this classes cant be used in it program");
        }

        HashMap<String, Method> gettersOfFromClass = getMethodsMap(methodsOfFromClass);
        HashMap<String, Method> settersOfFromClass = getMethodsMap(methodsOfToClass);

        for (Field field : fieldsOfToClass) {
            String methodName = getMethodName(field.getName());
            if (gettersOfFromClass.containsKey("get" + methodName) && settersOfFromClass.containsKey("set" + methodName)) {
                Method satisfactoryMethod = gettersOfFromClass.get("get" + methodName);
                if (satisfactoryMethod.getReturnType().equals(field.getType()) ||
                        field.getType().getSuperclass().equals(satisfactoryMethod.getReturnType())) {
                    settersOfFromClass.get("set" + methodName).invoke(to, satisfactoryMethod.invoke(from));
                }
            }
        }
    }

    private static HashMap<String, Method> getMethodsMap(Method[] declaredMethods) {
        HashMap<String, Method> methodsMap = new HashMap<>();
        if (declaredMethods != null && declaredMethods.length > 0) {
            for (Method declaredMethod : declaredMethods) {
                methodsMap.put(declaredMethod.getName(), declaredMethod);
            }
        }
        return methodsMap;
    }

    private static String getMethodName(String name) {
        return name.substring(0, 1).toUpperCase() +
                name.substring(1);
    }
}
