package product;

/**
 * Created by User on 28.07.2017.
 */
public class Category {
    private String name;

    public Category(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
