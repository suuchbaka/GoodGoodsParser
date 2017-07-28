package product;

/**
 * Created by User on 28.07.2017.
 */
public class Product {
    private int id;
    private String name;

    private Category category;
    private Barcode barcode;

    private String description;
    private String consist;
    private String weight;
    private String expirationDate;
    private String energyConsist;
    private String storeRules;
    private String manufacturer;

    public Product(int id, String name, Category category, Barcode barcode, String description, String consist, String weight, String expirationDate, String energyConsist, String storeRules, String manufacturer) {
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
}
