package product;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by User on 28.07.2017.
 */
public class Category {
    private String name;
    private ArrayList<Barcode> barcodes;

    public Category(String name) {
        this.name = name;
    }

    public ArrayList<Barcode> getBarcodes() {
        return barcodes;
    }

    public void setBarcodes(ArrayList<Barcode> barcodes) {
        this.barcodes = barcodes;
    }

    @Override
    public String toString() {
        return name;
    }
}
