package ua.com.alevel;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileIO {

    public void create(String path) throws IOException {
        System.out.println("path = " + path);
        File file = new File(path);
        boolean newFile = file.createNewFile();
        boolean isFile = file.isFile();
        System.out.println("isFile = " + isFile);
    }

    public void createDir(String path) throws IOException {
        System.out.println("path = " + path);
        File file = new File(path);
        boolean newFile = file.mkdir();
        boolean isDirectory = file.isDirectory();
        System.out.println("isDirectory = " + isDirectory);
    }

    public void createDirs(String path) throws IOException {
        System.out.println("path = " + path);
        File file = new File(path);
        boolean newFile = file.mkdirs();
        boolean isDirectory = file.isDirectory();
        System.out.println("isDirectory = " + isDirectory);
    }

    public void remove(String path) throws IOException {
        System.out.println("path = " + path);
        File file = new File(path);
        try {
            Thread.sleep(5000);
            boolean delete = file.delete();
            System.out.println("delete = " + delete);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeDir(String path) throws IOException {
        System.out.println("path = " + path);
        File file = new File(path);
//        boolean delete = file.delete();
//        System.out.println("delete = " + delete);

        FileUtils.deleteDirectory(file);
    }

    public void observ(String path) {
        File file = new File(path);
        System.out.println("file = " + file.getAbsolutePath());
        readDir(file);
    }

    private void readDir(File dir) {
        System.out.println("dir = " + dir.getAbsolutePath());
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                readDir(file);
            } else {
                System.out.println("file = " + file.getAbsolutePath());
            }
        }
    }
}
