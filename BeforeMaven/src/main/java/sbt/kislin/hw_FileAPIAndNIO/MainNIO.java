package sbt.kislin.hw_FileAPIAndNIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.*;

/**
 * Created by axel on 07.09.2016.
 */
public class MainNIO {
    public static final String PATH = "D:\\axel\\hello.txt";
    public static final String RELATIVE_PATH = ".\\axel";
    public static final String FAKE_PATH_1 = "D:\\sbt_training\\HomeWorks\\.gitignore";
    public static final String FAKE_PATH_2 = ".\\.gitignore";
    private static Logger LOGGER = LogManager.getRootLogger();


    public static void main(String[] args) {
        toyingWithPathClass(PATH);
        LOGGER.info("***************RELATIVE PATHS*****************");
        toyingWithRelativePaths(RELATIVE_PATH);
        NIOFilesToying();
        try {
            FileWalker visitor = new FileWalker();
            Files.walkFileTree(Paths.get(FAKE_PATH_1).getParent(),visitor);
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    private static void NIOFilesToying() {

        Path path1 = Paths.get(FAKE_PATH_1);
        Path path2 = Paths.get(FAKE_PATH_2); // а чего бы не сравнить с гитигнором то...

        LOGGER.info("SameFiles method: ");
        try {
            LOGGER.info("Files.isSameFile(path1, path2) is: "+ Files.isSameFile(path1, path2));
        } catch (IOException e) {
            LOGGER.info(e);
        }
        LOGGER.info("Info about directory and smth in their");
        if (Files.exists(path1, LinkOption.NOFOLLOW_LINKS)) {
            LOGGER.info("Existing path");
            if (Files.isDirectory(path1, LinkOption.NOFOLLOW_LINKS)) {
                LOGGER.info(path1.toString() + " is directory");
            } else {
                LOGGER.info(path1.toString() + " is a file");
            }
        } else {
            LOGGER.info("Path " + path1 + " is not exist");
        }
        LOGGER.info("whats options with it file?");
        LOGGER.info("Readable: %b, Writable: %b, Executable: %b ",
                Files.isReadable(path1), Files.isWritable(path1),
                Files.isExecutable(path1));
        LOGGER.info("trying to access attribute");
        try {
            LOGGER.info("Last modified time is: " + Files.getAttribute(path1, "lastModifiedTime"));
            LOGGER.info("Size of file: " + Files.getAttribute(path1, "size"));
            LOGGER.info("Creation time: " + Files.getAttribute(path1, "creationTime"));
        } catch (IOException e) {
            LOGGER.error(e);
        }
        LOGGER.info("trying copy files");
        try {
            Path notFakePath = Paths.get("D:\\axel\\");
            Files.copy(path1, notFakePath.resolve(path1.getFileName()));
            LOGGER.info("Success copying!");
        } catch (IOException e) {
            LOGGER.error(e);
        }
        LOGGER.info("trying move file");
        try {
            Files.copy(Paths.get(PATH), path1.getRoot().resolve(path1.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            LOGGER.info("Success moving");
        } catch (IOException e) {
            LOGGER.error(e);
        }

        LOGGER.info("Deleting this file, what be moved");
        try {
            Files.delete(path1.getRoot().resolve(path1.getFileName()));
            LOGGER.info("Success deletion!");
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    private static void toyingWithPathClass(String stringPath) {
        Path nioFilePath = Paths.get(stringPath);
        System.out.println("Info about file from NIO Path class:");
        System.out.println("name of file: " + nioFilePath.getFileName());
        System.out.println("Root of PATH: " + nioFilePath.getRoot().toString());
        System.out.println("trying convert PATH to real PATH (???) :");
        try {
            LOGGER.info(nioFilePath.toRealPath());
        } catch (IOException e) {
            LOGGER.error(e);
        }
        System.out.println("FileSystem of it PATH:" + nioFilePath.getFileSystem().toString());
        System.out.println("Parent catalog for it file: " + nioFilePath.getParent().toString());
        System.out.println("We can know, how elements in the path hierarchy! ");
        System.out.println("getNameCount() is " + nioFilePath.getNameCount());
        System.out.println("We can for-each itteration on NIO.Path object, like this: ");
        for (Path element : nioFilePath) {
            LOGGER.info(element);
        }
        System.out.println("we can access to element hierarchy with him index:");
        System.out.println("Element with index 0 is " + nioFilePath.getName(0));
    }

    private static void toyingWithRelativePaths(String stringPath) {
        Path relativePath = Paths.get(stringPath);
        System.out.println("FileName of it file: " + relativePath.getFileName());
        System.out.println("To URi it path: " + relativePath.toUri());
        System.out.println("Absolute path of it rel: " + relativePath.toAbsolutePath());
        System.out.println("count of hierarchy elements: " + relativePath.getNameCount());
        Path normalizedPath = relativePath.normalize();
        Path normPathFromStaticClass = Paths.get(relativePath.normalize().toString());
        System.out.println("Normalization path? " + normalizedPath);
        System.out.println("Another method of catching normalized path result: " + normPathFromStaticClass);
        System.out.println("Equals 2 paths? " + normalizedPath.equals(normPathFromStaticClass));
        System.out.println("Absolute path from normalized is: " + normalizedPath.toAbsolutePath());
        System.out.println("Cool, this path like windows explorer paths");
        try {
            System.out.println("Real rel path is: " + relativePath.toRealPath(LinkOption.NOFOLLOW_LINKS));
            System.out.println("Real normalized rel path is: " + normalizedPath.toRealPath(LinkOption.NOFOLLOW_LINKS));
            //падаем в эксепшн с этими двумя методами, почему?
        } catch (IOException e) {
            LOGGER.error(e);
        }
        System.out.println("Equals rel and normalized paths: " + normalizedPath.equals(relativePath));
        for (Path element : relativePath) {
            System.out.println("For each with relative path:" + element);
        }
        for (Path element : normalizedPath) {
            System.out.println("For each with normalized relative path:" + element);
        }
        for (Path element : normalizedPath.toAbsolutePath()) {
            System.out.println("For each with normalized absolute relative path:" + element);
        }
    }
}
