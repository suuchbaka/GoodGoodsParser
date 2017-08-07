package product;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by User on 28.07.2017.
 */

@Embeddable
public class Barcode implements Serializable {

    private static final long serialVersionUID = 8859548184246276106L;

    @Column(name = "BARCODE", unique = true, columnDefinition = "TEXT")
    private String barcode;

    public Barcode(String barcode) {
        this.barcode = barcode;
    }

    public Barcode() {
    }

    @Override
    public String toString() {
        return barcode;
    }
}
