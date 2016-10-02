package sbt.kislin.hw_FileAPIAndNIO;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by axel on 07.09.2016.
 */
public class FileWalker extends SimpleFileVisitor {
    @Override
    public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) throws IOException {
        Path p = (Path)dir;
        if (p.getFileName().toString().equals(".git"))
        {
            return FileVisitResult.SKIP_SUBTREE;
        }
        System.out.println("second visited: "+p.getFileName());
        System.out.println("this is a directory "+p.toFile().isDirectory());
        return super.preVisitDirectory(dir, attrs);
    }

    @Override
    public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
        Path p = (Path)file;
        if (p.getFileName().toString().equals(".git"))
        {
            return FileVisitResult.SKIP_SIBLINGS;
        }
        System.out.println("now visited: "+p.getFileName());
        System.out.println("this is a directory "+p.toFile().isDirectory());
        System.out.println("this is a file "+p.toFile().isFile());
        System.out.println("this file have nameCounts "+p.getNameCount());
        return super.visitFile(file, attrs);
    }

    @Override
    public FileVisitResult visitFileFailed(Object file, IOException exc) throws IOException {
        Path p = (Path)file;
        System.out.println("Cant access to "+p.getFileName());
        return super.visitFileFailed(file, exc);
    }
}
