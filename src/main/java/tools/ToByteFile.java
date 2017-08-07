package tools;

import com.mchange.v2.io.FileUtils;
import org.apache.commons.lang3.SerializationUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class ToByteFile {

    private byte[] bytesArray;

    public ToByteFile(Object o) throws IOException {
        bytesArray = SerializationUtils.serialize((Serializable) o);
        writeDataToFile();
    }

    private void writeDataToFile() throws IOException {
        try (FileOutputStream fs = new FileOutputStream("category.dat")) {
            fs.write(bytesArray);
        }
    }
}
