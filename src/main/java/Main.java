import org.jsoup.select.Elements;
import parser.ParserManager;
import product.Barcode;
import product.Category;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {

        ParserManager parserManager = new ParserManager(Variables.CATALOGUE_URL);

        Elements categories = parserManager.getCategoriesAsElements();

        LinkedHashMap<String, String> links = parserManager.getLinksToCategory(categories);
        LinkedList<Category> categoryLinkedList = new LinkedList<>();

        for(String s : links.keySet()) {
            LinkedList<Barcode> barcodes = parserManager.parseBarcodesInCategory(links.get(s));

            Category category = new Category(s);
            category.setBarcodes(barcodes);

            categoryLinkedList.add(category);
        }
    }
}
