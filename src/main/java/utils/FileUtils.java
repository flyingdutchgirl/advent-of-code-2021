package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileUtils {

    public static Scanner getScannerFromFile(String filepath) throws FileNotFoundException {
        File file = new File(filepath);

        return new Scanner(file);
    }


}
