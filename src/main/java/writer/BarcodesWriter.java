package writer;

import product.Barcode;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by User on 03.08.2017.
 */
public class BarcodesWriter {

    private File file;
    private BufferedWriter bw = null;
    private FileWriter fw = null;

    public BarcodesWriter(File file) {
        this.file = file;
    }

    public void writeBarcodesToFile(LinkedList<Barcode> barcodes) throws IOException {
        try {
            fw = new FileWriter(file, true);
            bw = new BufferedWriter(fw);

            for(Barcode barcode : barcodes) {
                bw.write(barcode.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bw != null) {
                bw.close();
            }

            if(fw != null) {
                fw.close();
            }
        }
    }
}
