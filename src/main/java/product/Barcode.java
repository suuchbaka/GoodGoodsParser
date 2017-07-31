package product;

/**
 * Created by User on 28.07.2017.
 */

public class Barcode {
    private String barcode;

    public Barcode(String barcode) {
        this.barcode = barcode;
    }

    @Override
    public String toString() {
        return barcode;
    }
}
