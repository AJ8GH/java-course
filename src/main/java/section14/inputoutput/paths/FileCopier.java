package section14.inputoutput.paths;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class FileCopier extends SimpleFileVisitor<Path> {
    private Path sourceRoot;
    private Path targetRoot;

    public FileCopier(Path sourceRoot, Path targetRoot) {
        this.sourceRoot = sourceRoot;
        this.targetRoot = targetRoot;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        System.out.println("Error accessing file: " +
                file.toAbsolutePath() + " : " + exc.getMessage());
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        Path relativisedPath = sourceRoot.relativize(dir);
        System.out.println("Relativised path = " + relativisedPath);
        Path copyDir = targetRoot.resolve(relativisedPath);
        System.out.println("Resolved path for copy = " + copyDir);

        try {
            Files.copy(dir, copyDir);
        } catch (IOException e) {
            e.printStackTrace();
            return FileVisitResult.SKIP_SUBTREE;
        }

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Path relativisedPath = sourceRoot.relativize(file);
        System.out.println("Relativised path = " + relativisedPath);
        Path copyDir = targetRoot.resolve(relativisedPath);
        System.out.println("Resolved path for copy = " + copyDir);

        try {
            Files.copy(file, copyDir);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return FileVisitResult.CONTINUE;
    }
}
