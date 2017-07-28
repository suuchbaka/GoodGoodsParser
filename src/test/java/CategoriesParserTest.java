import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parser.CategoriesParser;

/**
 * Created by User on 28.07.2017.
 */

public class CategoriesParserTest {

    private CategoriesParser categoriesParser;
    private Elements catalogueTable;

    @Before
    public void setUp() throws Exception {
        categoriesParser = new CategoriesParser(Variables.CATALOGUE_URL);
    }

    @After
    public void tearDown() throws Exception {
        testProductGroupsParser();
        testElementsToLinks();
    }

    @Test
    public void testProductGroupsParser() throws Exception {
        catalogueTable = categoriesParser.getAllProductGroupsInCatalogue();
        Assert.assertNotNull(catalogueTable);
    }

    @Test
    public void testElementsToLinks() throws Exception {
        for(Element e : catalogueTable) {
            String link = categoriesParser.getLinkToProductGroupAsString(e);
            Assert.assertTrue(link.contains("http"));
        }
    }
}
