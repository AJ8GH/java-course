package section14.inputoutput.paths;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        Path workingDirectoryPath = FileSystems.getDefault().getPath("working_directory_file.txt");
        printFile(workingDirectoryPath);

        Path subdirectoryPath = FileSystems.getDefault().getPath("files", "subdirectory_file.txt");
        printFile(subdirectoryPath);
    }

    private static void printFile(Path path) {
        try (BufferedReader fileReader = Files.newBufferedReader(path)) {
            String line;
            while ((line = fileReader.readLine()) !=null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
