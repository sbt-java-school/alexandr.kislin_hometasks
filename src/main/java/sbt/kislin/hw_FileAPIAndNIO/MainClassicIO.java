package sbt.kislin.hw_FileAPIAndNIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by axel on 06.09.2016.
 */
public class MainClassicIO {
    private final static String pathFile = "D://axel//hello.txt";

    public static void main(String[] args) {
        System.out.println("Мы считали из файла: ");
        readFromFile(pathFile);
        System.out.println("");
        String text = "adding some in file3433";
        System.out.println("Мы перепишем в файл строку " + text + " и заново считаем: ");
        writeToFileString(pathFile, text);
        readFromFile(pathFile);
        System.out.println("");
        System.out.println("А теперь считаем это всё в StringBuffer");
        StringBuffer textFromFile = getStringBufferFromFile(pathFile);
        System.out.println("И распечатаем");
        System.out.println(textFromFile);
    }

    private static StringBuffer getStringBufferFromFile(String pathFile) {
        StringBuffer buff = new StringBuffer();
        try (FileInputStream fin = new FileInputStream(pathFile);) {
            int i = -1;
            while ((i = fin.read()) != -1) {
                buff.append((char) i);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return buff;
    }

    private static void writeToFileString(String pathFile, String text) {
        try (FileOutputStream fos = new FileOutputStream(pathFile)) {
            byte[] buffer = text.getBytes();
            fos.write(buffer, 0, buffer.length);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void readFromFile(String path) {
        try (FileInputStream fin = new FileInputStream(path);) {
            int i = -1;
            while ((i = fin.read()) != -1) {
                System.out.print((char) i);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
