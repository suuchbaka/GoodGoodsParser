import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parser.CategoriesParser;
import parser.ProductParser;
import product.Barcode;
import product.Category;
import product.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class ProductParserTest {

    private LinkedHashMap<String, String> links;
    private CategoriesParser categoriesParser;
    private ProductParser productParser;

    @Before
    public void setUp() throws Exception {
        categoriesParser = new CategoriesParser(Variables.CATALOGUE_URL);
        productParser = new ProductParser();
        links = new LinkedHashMap<>();

        Elements categories = categoriesParser.getAllProductGroupsInCatalogue();
        for(Element e : categories) {
            links.put(e.text(), categoriesParser.getLinkToProductGroupAsString(e));
        }
    }

    @Test
    public void testParsingProducts() throws IOException {
        ArrayList<Product> products = new ArrayList<>();
        int index = 0;
        for(String s : links.keySet()) {
            ArrayList<Barcode> barcodes = categoriesParser.getAllBarcodesByCategory(links.get(s));

            for(Barcode b : barcodes) {
                products.add(productParser.parseProduct(new Category(s), b));
            }
        }

        for(Product p : products) {
            Assert.assertNotNull(p);
        }
    }
}
