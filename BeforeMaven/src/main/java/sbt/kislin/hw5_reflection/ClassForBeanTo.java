package sbt.kislin.hw5_reflection;

/**
 * Created by Александр on 01.09.2016.
 */
public class ClassForBeanTo {
    private String fio;

    @Deprecated
    public String getFio() {
        return fio;
    }

    public void setFio(String value) {
        this.fio = value;
    }

    public ClassForBeanTo(String name) {
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
