package tools;

import org.apache.commons.lang3.SerializationUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FromByteFile {

    public FromByteFile() throws FileNotFoundException {

    }

    public Object deserializeObject(String pathToFile) throws IOException {
        return SerializationUtils.deserialize(readFromFile(pathToFile));
    }

    private byte[] readFromFile(String pathToFile) throws IOException {
        return Files.readAllBytes(Paths.get(pathToFile));
    }
}
