package utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileManager {

    private static final Path DATA_FOLDER = Path.of("data");

    public FileManager() {
        createDataFolder();
    }

    private void createDataFolder() {

        try {

            if (!Files.exists(DATA_FOLDER)) {
                Files.createDirectory(DATA_FOLDER);
            }

        } catch (IOException e) {

            System.out.println(
                    "Unable to create data folder: "
                    + e.getMessage()
            );
        }
    }

    public void saveData(String fileName, String data) {

        Path filePath = DATA_FOLDER.resolve(fileName);

        try {

            Files.writeString(
                    filePath,
                    data + System.lineSeparator(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING
            );

            System.out.println("Data saved successfully.");

        } catch (IOException e) {

            System.out.println(
                    "Error while saving data: "
                    + e.getMessage()
            );
        }
    }

    public void appendData(String fileName, String data) {

        Path filePath = DATA_FOLDER.resolve(fileName);

        try {

            Files.writeString(
                    filePath,
                    data + System.lineSeparator(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );

        } catch (IOException e) {

            System.out.println(
                    "Error while appending data: "
                    + e.getMessage()
            );
        }
    }

    public List<String> readData(String fileName) {

        Path filePath = DATA_FOLDER.resolve(fileName);

        try {

            if (Files.exists(filePath)) {
                return Files.readAllLines(filePath);
            }

        } catch (IOException e) {

            System.out.println(
                    "Error while reading data: "
                    + e.getMessage()
            );
        }

        return List.of();
    }

    public boolean fileExists(String fileName) {

        Path filePath = DATA_FOLDER.resolve(fileName);

        return Files.exists(filePath);
    }
}