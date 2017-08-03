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
