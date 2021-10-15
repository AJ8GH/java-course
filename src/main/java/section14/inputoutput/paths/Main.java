package section14.inputoutput.paths;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Main {
    public static void main(String[] args) {
         createFile();
    }

    private static void examplesWithFilePaths() {
        Path workingDirectoryPath = FileSystems.getDefault().getPath("working_directory_file.txt");
        printFile(workingDirectoryPath);

        //        Path subdirectoryPath = FileSystems.getDefault().getPath("files", "subdirectory_file.txt");
        Path subdirectoryPath = Paths.get(".", "files", "subdirectory_file.txt");
        printFile(subdirectoryPath);

        //        Path externalFilePath = Paths.get("/Users/jonasa/projects/aj/out_there.txt");
        Path externalFilePath = Paths.get("/Users", "/jonasa", "/projects", "/aj", "out_there.txt");
        printFile(externalFilePath);

        workingDirectoryPath = Paths.get(".");
        System.out.println(workingDirectoryPath.toAbsolutePath());

        Path abnormalPath = FileSystems.getDefault().getPath(".", "files", "..", "files", "subdirectory_file.txt");
        System.out.println(abnormalPath.toAbsolutePath());
        System.out.println(abnormalPath.normalize().toAbsolutePath());

        Path nonExistentPath = FileSystems.getDefault().getPath("nonExistentFile.txt");
        System.out.println(nonExistentPath.toAbsolutePath());

        Path fictitiousPath = Paths.get("/Users/jonasa/projects", "aj", "whatever.txt");
        System.out.println(fictitiousPath.toAbsolutePath());
        Path realPath = FileSystems.getDefault().getPath("files");

        System.out.println(Files.exists(realPath));
        System.out.println(Files.notExists(realPath));

        System.out.println(Files.exists(fictitiousPath));
        System.out.println(Files.notExists(fictitiousPath));
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

    private static void copyFile() {
        try {
            Path sourceFile = FileSystems.getDefault().getPath("examples", "file1.txt");
            Path copyFile = FileSystems.getDefault().getPath("examples", "file1_copy.txt");
            Files.copy(sourceFile, copyFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void moveFile() {
        try {
            Path fileToMove = FileSystems.getDefault().getPath("examples", "file1_copy.txt");
            Path destination = FileSystems.getDefault().getPath("examples", "dir1", "file1_copy.txt");
            Files.move(fileToMove, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deleteFile() {
        try {
            Path fileToDelete = Paths.get("examples", "file1_copy.txt");
            Files.deleteIfExists(fileToDelete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createFile() {
        try {
            Path dirToCreate = Paths.get("examples","newdirectory");
            Files.createDirectory(dirToCreate);

            Path fileToCreate = Paths.get("examples", "newdirectory", "new_file.txt");
            Files.createFile(fileToCreate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
