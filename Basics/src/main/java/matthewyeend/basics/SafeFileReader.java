/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.basics;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 *
 * @author matthewyeend
 */
public final class SafeFileReader {

    private static final long MAX_FILE_SIZE_BYTES = 1_048_576L; // 1 MiB

    private SafeFileReader() {
    }

    public static List<String> readLines(Path baseDirectory, String fileName) throws IOException {
        Path base = baseDirectory.toRealPath();
        Path target = base.resolve(fileName).normalize();

        // Block path traversal such as "../../etc/passwd"
        if (!target.startsWith(base)) {
            throw new SecurityException("Access outside the permitted directory is not allowed");
        }
        // Block symbolic links escaping the base directory
        Path realTarget = target.toRealPath();
        if (!realTarget.startsWith(base)) {
            throw new SecurityException("Access outside the permitted directory is not allowed");
        }
        if (!Files.isRegularFile(realTarget) || Files.size(realTarget) > MAX_FILE_SIZE_BYTES) {
            throw new IllegalArgumentException("File is not a regular file or exceeds the size limit");
        }
        return Files.readAllLines(realTarget, StandardCharsets.UTF_8);
    }
}