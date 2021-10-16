package section14.inputoutput.paths;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

public class Main {
    public static void main(String[] args) {
        fileStores();
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

    private static void createDirectories() {
        try {
            Path dirToCreate = Paths.get("examples", "dir2/dir3/dir4/dir5/dir6");
            Files.createDirectories(dirToCreate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getFileAttributes() {
        try {
            Path filePath = Paths.get("examples", "dir1", "file1.txt");
            long size = Files.size(filePath);
            FileTime lastModified = Files.getLastModifiedTime(filePath);
            System.out.println("Size = " + size);
            System.out.println("Last modified at = " + lastModified);

            BasicFileAttributes attributes = Files.readAttributes(filePath, BasicFileAttributes.class);
            System.out.println("Size = " + attributes.size());
            System.out.println("Last modified at = " + attributes.lastModifiedTime());
            System.out.println("Created at = " + attributes.creationTime());
            System.out.println("Is directory = " + attributes.isDirectory());
            System.out.println("Is regular file = " + attributes.isRegularFile());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readExistingDirectoryContents() {
        DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
            public boolean accept(Path path) throws IOException {
                return Files.isRegularFile(path);
            }
        };

        Path directory = FileSystems.getDefault().getPath("examples/dir2");
        try (DirectoryStream<Path> contents = Files.newDirectoryStream(directory, filter)) {
            for (Path file : contents) {
                System.out.println(file.getFileName());
            }
        } catch (IOException | DirectoryIteratorException e) {
            e.printStackTrace();
        }
    }

    private static void filterDirectoriesWithLambda() {
        DirectoryStream.Filter<Path> filter = Files::isRegularFile;

        Path directory = FileSystems.getDefault().getPath("examples/dir2");
        try (DirectoryStream<Path> contents = Files.newDirectoryStream(directory, filter)) {
            for (Path file : contents) {
                System.out.println(file.getFileName());
            }
        } catch (IOException | DirectoryIteratorException e) {
            e.printStackTrace();
        }
    }

    private static void osAgnosticSeparator() {
        String separator = File.separator;
        System.out.println(separator);

        separator = FileSystems.getDefault().getSeparator();
        System.out.println(separator);
    }

    private static void createTemporaryFile() {
        try {
            Path tempFile = Files.createTempFile("myapp", ".appext");
            System.out.println("Temporary file path = " + tempFile.toAbsolutePath());
//            Temporary file path = /var/folders/x_/0655n6d1587_ctpk__mbtsvsn80rmp/T/myapp11807715093333568973.appext
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fileStores() {
        Iterable<FileStore> stores = FileSystems.getDefault().getFileStores();
        for (FileStore fileStore : stores) {
            System.out.println("Volume name = " + fileStore.name());
            System.out.println("File store = " + fileStore);
        }

        System.out.println("**********************");
        Iterable<Path> rootPaths = FileSystems.getDefault().getRootDirectories();
        for (Path path : rootPaths) {
            System.out.println("Root path = " + path);
        }
    }

    private static void walkFileTree() {
        
    }
}
