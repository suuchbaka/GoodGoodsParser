package product;

import org.hibernate.annotations.OptimisticLock;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.Parent;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by User on 28.07.2017.
 */

@Entity
@Table(name = "ProductV3", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ID"),
        @UniqueConstraint(columnNames = "BARCODE    ")
})
public class Product implements Serializable {

    private static final long serialVersionUID = 3592329866577805843L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKeyJoinColumn(name = "ID")
    private int id;
    @Column(name = "NAME", unique = true, nullable = false, columnDefinition = "TEXT")
    private String name;

    @Embedded
    private Category category;
    @Embedded
    private Barcode barcode;

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    private String description;
    @Column(name = "CONSIST", columnDefinition = "TEXT")
    private String consist;
    @Column(name = "WEIGHT", columnDefinition = "TEXT")
    private String weight;
    @Column(name = "EXPIRATION_DATE", columnDefinition = "TEXT")
    private String expirationDate;
    @Column(name = "ENERGY_CONSIST", columnDefinition = "TEXT")
    private String energyConsist;
    @Column(name = "STORE_RULES", columnDefinition = "TEXT")
    private String storeRules;
    @Column(name = "MANUFACTURER", columnDefinition = "TEXT")
    private String manufacturer;

    public Product() {
        this.id = id;
        this.name = name;
        this.category = category;
        this.barcode = barcode;
        this.description = description;
        this.consist = consist;
        this.weight = weight;
        this.expirationDate = expirationDate;
        this.energyConsist = energyConsist;
        this.storeRules = storeRules;
        this.manufacturer = manufacturer;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public Barcode getBarcode() {
        return barcode;
    }

    public String getDescription() {
        return description;
    }

    public String getConsist() {
        return consist;
    }

    public String getWeight() {
        return weight;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getEnergyConsist() {
        return energyConsist;
    }

    public String getStoreRules() {
        return storeRules;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setBarcode(Barcode barcode) {
        this.barcode = barcode;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setConsist(String consist) {
        this.consist = consist;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setEnergyConsist(String energyConsist) {
        this.energyConsist = energyConsist;
    }

    public void setStoreRules(String storeRules) {
        this.storeRules = storeRules;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
