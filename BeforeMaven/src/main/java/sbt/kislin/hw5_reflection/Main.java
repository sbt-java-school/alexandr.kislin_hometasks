package sbt.kislin.hw5_reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 25.08.2016.
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        final Class<?> aClass = Class.forName("sbt.kislin.hw5_reflection.MainClassicIO$Test");
        printSuperclasses(aClass);
        printAllMethodsOfItClass(aClass);
        printAllParentsMethod(aClass);
        printAllFields(aClass);
        printAllConstructors(aClass);
    }

    private static void printAllConstructors(Class<?> aClass) {
        System.out.println("***All constructors of class " + aClass.getName() + " ****");
        Constructor<?>[] constructors = aClass.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            StringBuilder builder = new StringBuilder();
            if (constructor.getDeclaredAnnotations().length != 0) {
                Annotation[] declaredAnnotations = constructor.getDeclaredAnnotations();
                for (Annotation annotation : declaredAnnotations) {
                    builder.append(annotation);
                }
            }
            builder.append(getModifierInString(constructor.getModifiers())).append(" ");
            builder.append(constructor.getName()).append(" ");
            int parameterCount = constructor.getParameterCount();
            if (parameterCount != 0) {
                Class<?>[] parameters = constructor.getParameterTypes();
                for (Class<?> parameter : parameters) {
                    builder.append(parameter.getSimpleName()).append(" ");
                }
            }
            System.out.println(builder);
        }
    }

    private static void printSuperclasses(Class<?> testClass) {
        System.out.println("***Class hierarchy for class " + testClass.getName() + " ***");
        Class<?> currentClass = testClass;
        List<String> classList = new ArrayList<>();
        while (currentClass != null) {
            classList.add(currentClass.getName());
            currentClass = currentClass.getSuperclass();
        }
        for (int i = classList.size() - 1; i >= 0; i--) {
            System.out.println(classList.get(i));
            if (i != 0) {
                System.out.println("^");
            }
        }
    }

    private static void printAllMethodsOfItClass(Class<?> testClass) {
        System.out.println("***All methods of class " + testClass.getName() + " ***");
        Method[] declaredMethods = testClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(makeInfoAboutMethod(declaredMethod));
        }
    }

    private static void printAllParentsMethod(Class<?> testClass) {
        System.out.println("***All methods of parents class***");
        Class<?> currentClass = testClass;
        while (currentClass != Object.class) {
            currentClass = currentClass.getSuperclass();
            Method[] declaredMethods = currentClass.getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                System.out.println(makeInfoAboutMethod(declaredMethod));
            }
        }
    }

    private static String getModifierInString(int modifiers) {
        return Modifier.toString(modifiers);
    }

    private static StringBuilder makeInfoAboutMethod(Method declaredMethod) {
        StringBuilder builder = new StringBuilder();
        if (declaredMethod.getDeclaredAnnotations().length != 0) {
            Annotation[] declaredAnnotations = declaredMethod.getDeclaredAnnotations();
            for (Annotation annotation : declaredAnnotations) {
                builder.append(annotation);
            }
        }
        builder.append(getModifierInString(declaredMethod.getModifiers())).append(" ");
        builder.append(declaredMethod.getReturnType()).append(" ");
        builder.append(declaredMethod.getName()).append(" ");
        int parameterCount = declaredMethod.getParameterCount();
        if (parameterCount != 0) {
            Class<?>[] parameters = declaredMethod.getParameterTypes();
            for (Class<?> parameter : parameters) {
                builder.append(parameter.getSimpleName()).append(" ");
            }
        }
        return builder;
    }

    private static void printAllFields(Class<?> aClass) {
        System.out.println("***All fields of class " + aClass.getName() + " ***");
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            StringBuilder builder = new StringBuilder();
            builder.append(getModifierInString(declaredField.getModifiers())).append(" ");
            builder.append(declaredField.getName());
            System.out.println(builder);
        }
    }


    static class Test extends ParentTest<String> {
        private final String fio;

        @Deprecated
        public String getFio() {
            return fio;
        }

        public Test(String name) {
            fio = name;
        }

        public int add(int a, int b) {
            return a + b;
        }

        public void random() {
            System.out.println("We in random");
        }

        private void examplePrivateMethod() {
        }

        @Override
        public String toString() {
            return fio;
        }
    }

    static class ParentTest<T> extends GrandParentTest {
        @Deprecated
        public void parentPublicMethod() {
        }

        private void parentPrivateMethod() {
        }
    }

    static class GrandParentTest {
        public void grandParentsPublicMethod() {
        }

        private void grandParentsPrivateMethod() {
        }
    }

    class testProd extends Test {
        public testProd(String name) {
            super(name);
        }
    }
}
