package sbt.kislin.hw_FileAPIAndNIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by axel on 06.09.2016.
 */
public class MainClassicIO {
    private static Logger LOGGER = LogManager.getRootLogger();
    private final static String pathFile = "D://axel//hello.txt";

    public static void main(String[] args) {
        LOGGER.info("Мы считали из файла: ");
        readFromFile(pathFile);
        System.out.println("");
        String text = "adding some in file3433";
        LOGGER.info("Мы перепишем в файл строку " + text + " и заново считаем: ");
        writeToFileString(pathFile, text);
        readFromFile(pathFile);
        LOGGER.info("А теперь считаем это всё в StringBuffer");
        StringBuffer textFromFile = getStringBufferFromFile(pathFile);
        LOGGER.info("И распечатаем");
        LOGGER.info(textFromFile);
    }

    private static StringBuffer getStringBufferFromFile(String pathFile) {
        StringBuffer buff = new StringBuffer();
        try (FileInputStream fin = new FileInputStream(pathFile);) {
            int i = -1;
            while ((i = fin.read()) != -1) {
                buff.append((char) i);
            }
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return buff;
    }

    private static void writeToFileString(String pathFile, String text) {
        try (FileOutputStream fos = new FileOutputStream(pathFile)) {
            byte[] buffer = text.getBytes();
            fos.write(buffer, 0, buffer.length);
        } catch (IOException ex) {
            LOGGER.error(ex);
        }
    }

    private static void readFromFile(String path) {
        try (FileInputStream fin = new FileInputStream(path);) {
            int i = -1;
            while ((i = fin.read()) != -1) {
                System.out.print((char) i);
            }
        } catch (IOException e) {
            LOGGER.info(e);
        }
    }
}
