package product;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by User on 28.07.2017.
 */
public class Category {
    private String name;
    private LinkedList<Barcode> barcodes;

    public Category(String name) {
        this.name = name;
    }

    public LinkedList<Barcode> getBarcodes() {
        return barcodes;
    }

    public void setBarcodes(LinkedList<Barcode> barcodes) {
        this.barcodes = barcodes;
    }

    @Override
    public String toString() {
        return name;
    }
}
