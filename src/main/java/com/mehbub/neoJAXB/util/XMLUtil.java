package com.mehbub.neoJAXB.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class XMLUtil {

    private static Path getPath() {
        return Path.of("src", "main", "resources", "xml");
    }

    /**
     * This method will take fileName as string argument and then seek the file in target location and if found then return the file
     * Otherwise, file directory and/or file.xml will be created and returned.
     * // String fileName = "u1".concat(".xml");
     *
     * @param fileName
     * @return File
     */
    public static File getXMLFile(String fileName) {
        Path path = getPath();
        Path dir = Path.of(path.toString());
        Path pathToFile = dir.resolve(fileName);

        if (Files.notExists(pathToFile)) {
            try {
                Files.createDirectories(dir);
                Files.createFile(pathToFile);
            } catch (IOException e) {
                throw new RuntimeException("Can't write the file");
            }
        }
        return pathToFile.toFile();
    }

    public static File getXMLFileByID(Integer id) {
        return getXMLFilePathByID(id).toFile();
    }

    public static Path getXMLFilePathByID(Integer id) {
        String fileName = String.valueOf(id).concat(".xml");
        return Path.of(getPath().toString(), fileName);
    }

    public static List<File> getXMLFilesFromLocation() {
        try {
            return Files.list(Paths.get(getPath().toString()))
                    .filter(path -> path.toFile().isFile())
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
