package parser;

import com.sun.istack.internal.NotNull;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import product.Barcode;
import product.Category;
import product.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by User on 28.07.2017.
 */
public class ParserManager {

    private CategoriesParser categoriesParser;
    private ProductParser productParser;

    public ParserManager(String catalogueURL) {
        categoriesParser = new CategoriesParser(catalogueURL);
        productParser = new ProductParser();
    }

    @NotNull
    public Elements getCategoriesAsElements() throws IOException {
        return categoriesParser.getAllProductGroupsInCatalogue();
    }

    @NotNull
    public LinkedHashMap<String, String> getLinksToCategory(Elements categories) {
        LinkedHashMap<String, String> linksAsMap = new LinkedHashMap<>();

        for(Element e : categories) {
            linksAsMap.put(e.text(), categoriesParser.getLinkToProductGroupAsString(e));
        }

        return linksAsMap;
    }

    @NotNull
    public Product parseProduct(Category category, Barcode barcode) throws IOException {
        return productParser.parseProduct(category, barcode);
    }

    @NotNull
    public ArrayList<Barcode> parseBarcodesInCategory(String category) throws IOException {
        return categoriesParser.getAllBarcodesByCategory(category);
    }


    @NotNull
    static String checkStatusCode(int statusCode) {
        StringBuilder stringBuilder = new StringBuilder();

        if(statusCode == 200) {
            stringBuilder.append("STATUS CODE: OK!");
        }

        else {
            stringBuilder
                    .append("ERORR: STATUS CODE ")
                    .append(statusCode);
            new Exception("ERROR FETCHING URL ").printStackTrace();
        }

        return stringBuilder.toString();
    }
}
