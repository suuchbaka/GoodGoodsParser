package parser;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import product.Barcode;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by User on 28.07.2017.
 */
public class CategoriesParser {
    private String catalogueUrl;
    private int TIMEOUT = 30000000;

    public CategoriesParser(String catalogueUrl) {
        this.catalogueUrl = catalogueUrl;
    }

    public Elements getAllProductGroupsInCatalogue() throws IOException {
        Connection.Response productGroups = Jsoup.connect(catalogueUrl)
                .method(Connection.Method.GET)
                .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2")
                .maxBodySize(0)
                .execute();

        Document document = productGroups.parse();

        Elements links = new Elements();

        links.addAll(document.getElementsByAttributeValueContaining("id", "ctl00_ContentPH_GroupsDG_ctl"));

        return links;
    }

    public LinkedList<Barcode> getAllBarcodesByCategory(String categoryUrl) throws IOException {
        LinkedList<Barcode> barcodes = new LinkedList<>();

        Connection.Response categoryPage = Jsoup.connect(categoryUrl)
                .method(Connection.Method.GET)
                .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2")
                .maxBodySize(0)
                .timeout(TIMEOUT)
                .execute();

       System.out.println(ParserManager.checkStatusCode(categoryPage.statusCode()));

        Document document = categoryPage.parse();

        Elements barcodesElements = document.getElementsByAttributeValueContaining("id", "A2");

        for(Element e : barcodesElements) {
            barcodes.add(new Barcode(e.text()));
        }

        return barcodes;
    }

    public String getLinkToProductGroupAsString(Element catalogueElement) {
        if(catalogueElement.attr("href").contains("http")) {
            return catalogueElement.attr("href").replaceAll(" ", "");
        }
        else {
            StringBuilder sb = new StringBuilder();
            sb
                    .append("http://goodsmatrix.ru/")
                    .append(catalogueElement.attr("href"));

            return sb.toString().trim();
        }
    }

}
