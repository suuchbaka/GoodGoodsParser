import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import parser.CategoriesParser;
import product.Barcode;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ProductParserTest {
    @Before
    public void setUp() throws Exception {
        CategoriesParser categoriesParser = new CategoriesParser(Variables.CATALOGUE_URL);

        Elements categories = categoriesParser.getAllProductGroupsInCatalogue();

        ArrayList
        ArrayList<Barcode> barcodes = new ArrayList<>();

        for(Element e : categories){
            links.put(e.text(), categoriesParser.getLinkToProductGroupAsString(e));
        }

        for(String s : links.values()) {

        }
    }
}
