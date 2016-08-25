package sbt.kislin.hw5_reflection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 25.08.2016.
 */
public class Main {

    private static void printSuperclasses(Class<?> testClass) {
        Class<?> currentClass = testClass;
        List<String> classList = new ArrayList<>();
        while (currentClass != null) {
            classList.add(currentClass.getName());
            currentClass = currentClass.getSuperclass();
        }
        for (int i = classList.size()-1; i >= 0 ; i--) {
            System.out.println(classList.get(i));
            if (i!=0){
                System.out.println("^");
            }
        }
    }

    public static void main(String[] args) {
        printSuperclasses(Test.class);
    }

    static class Test extends ParentTest<String> {
        private final String fio;

        public Test(String name) {
            fio = name;
        }

        public int add(int a, int b) {
            return a + b;
        }

        public void random() {
            System.out.println("We in random");
        }

        @Override
        public String toString() {
            return fio;
        }
    }

    static class ParentTest<T> extends GrandParentTest {
    }

    static class GrandParentTest {
    }

    class testProd extends Test {
        public testProd(String name) {
            super(name);
        }
    }
}
