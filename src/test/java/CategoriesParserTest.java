import junit.framework.AssertionFailedError;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parser.CategoriesParser;
import product.Barcode;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;

/**
 * Created by User on 28.07.2017.
 */

public class CategoriesParserTest {

    private CategoriesParser categoriesParser;
    private Elements catalogueTable;

    private LinkedHashMap<String, String> links;

    @Before
    public void setUp() throws Exception {
        links = new LinkedHashMap<>();
        categoriesParser = new CategoriesParser(Variables.CATALOGUE_URL);

        catalogueTable = categoriesParser.getAllProductGroupsInCatalogue();

        for(Element e : catalogueTable){
            links.put(e.text(), categoriesParser.getLinkToProductGroupAsString(e));
        }
    }

    @Test
    public void testProductGroupsParser() throws Exception {
        for(final Element expected : catalogueTable) {
            Assert.assertNotNull(expected);
            System.out.println(expected.text());
        }
    }

    @Test
    public void testLinks() throws Exception {
        for(final String s : links.values()) {
            Assert.assertTrue(s.startsWith("http"));
            Assert.assertFalse(s.contains("&nbsp"));
        }
    }

    @Test
    public void testGettingAllBarcodesByCategory() throws Exception{
        int totalBarcodes = 0;
        for(String s : links.values()) {
            ArrayList<Barcode> barcodes = categoriesParser.getAllBarcodesByCategory(s);

            totalBarcodes+= barcodes.size();

            for(Barcode b : barcodes) {
                Assert.assertFalse(b.toString().contains(" "));
                Assert.assertNotNull(b);
                System.out.println("Barcode: " + b.toString());
            }
        }

        System.out.println("TOTAL BARCODES: " + totalBarcodes);
    }
}
