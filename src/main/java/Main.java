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
        System.out.println("Парсим категории...");

        LinkedHashMap<String, String> links = parserManager.getLinksToCategory(categories);
        System.out.println("Берем ссылки из каталагов");

        LinkedList<Category> categoryLinkedList = new LinkedList<>();

        for(String s : links.keySet()) {
            LinkedList<Barcode> barcodes = parserManager.parseBarcodesInCategory(links.get(s));
            System.out.println("Парсим штрихкоды...");

            Category category = new Category(s);
            category.setBarcodes(barcodes);

            categoryLinkedList.add(category);

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder
                    .append("Добавлено ")
                    .append(barcodes.size())
                    .append(" штрихкодов в категорию ")
                    .append(s)
                    .append("\n")
                    .append(links.get(s));

            System.out.println(stringBuilder.toString());
        }
    }
}
