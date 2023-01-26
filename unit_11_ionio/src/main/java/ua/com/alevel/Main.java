package ua.com.alevel;

import java.io.IOException;

public class Main {

    private static final String FILE = "file.txt";
    private static final String FILE_UPDATE = "update_file.txt";
    private static final String FILE_HIDDEN = ".file.txt";
    private static final String DIR = "folder";
    private static final String DIRS = "folder/folder1/folder2";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        FileIO fileIO = new FileIO();
//        fileIO.create(FILE);
//        fileIO.createDir(DIR);
//        fileIO.createDirs(DIRS);
//        fileIO.remove(FILE);
//        fileIO.observ(DIR);
//        fileIO.removeDir(DIR);
//        RWFile rwFile = new RWFile();
//        rwFile.read(FILE);
//        rwFile.write();
        SerialTest serialTest = new SerialTest();
        serialTest.test();
    }
}
