package product;

/**
 * Created by User on 28.07.2017.
 */

public class Barcode {
    private long barcode;

    public Barcode(long barcode) {
        this.barcode = barcode;
    }

    public long getBarcode() {
        return barcode;
    }

    @Override
    public String toString() {
        return String.valueOf(barcode);
    }
}
