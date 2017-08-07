package product;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by User on 28.07.2017.
 */

@Embeddable
public class Category implements Serializable {


    private static final long serialVersionUID = 7328745552400299235L;

    @Column(name = "CATEGORY", columnDefinition = "TEXT")
    private String name;
    @Transient
    private ArrayList<Barcode> barcodes;

    public Category(String name) {
        this.name = name;
    }

    public Category() {
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
