package parser;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import product.Barcode;
import product.Category;
import product.Product;

import java.io.IOException;

/**
 * Created by User on 28.07.2017.
 */
public class ProductParser {

    private final String productURL = "http://www.goodsmatrix.ru/goods/";
    private int TIMEOUT = 30000000;
    int totalProducts = 0;

    public ProductParser() {}

    public Product parseProduct(Category category, Barcode barcode) throws IOException {
        String url = productURL + barcode.toString().trim() + ".html";
        Connection.Response productPage = Jsoup.connect(url)
                .method(Connection.Method.GET)
                .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2")
                .maxBodySize(0)
                .timeout(TIMEOUT)
                .execute();

        Document document = productPage.parse();

        totalProducts++;

        Product product = new Product();
        product.setId(totalProducts);
        product.setCategory(category);
        product.setBarcode(barcode);
        product.setName(parseProductName(document));
        product.setDescription(parseProductDescription(document));
        product.setConsist(parseProductConsist(document));
        product.setWeight(parseProductWeight(document));
        product.setExpirationDate(parseDocumentExpirationDate(document));
        product.setEnergyConsist(parseEnergyConsist(document));
        product.setStoreRules(parseStoreRules(document));
        product.setManufacturer(parseProductManufacturer(barcode.toString()));

        return product;
    }

    private String parseProductName(Document document) {
        return document.select("#ctl00_ContentPH_GoodsName").text().replaceAll("\\xFFFD", "");
    }

    private String parseProductDescription(Document document) {
        return document.select("#ctl00_ContentPH_Comment").text().replaceAll("\\xFFFD", "");
    }

    private String parseProductConsist(Document document) {
        return document.select("#ctl00_ContentPH_Composition").text().replaceAll("\\xFFFD", "");
    }

    private String parseProductWeight(Document document) {
        return document.select("#ctl00_ContentPH_Net").text().replaceAll("\\xFFFD", "");
    }

    private String parseDocumentExpirationDate(Document document) {
        return document.select("#ctl00_ContentPH_KeepingTime").text().replaceAll("\\xFFFD", "");
    }

    private String parseEnergyConsist(Document document) {
        return document.select("#ctl00_ContentPH_ESL").text().replaceAll("\\xFFFD", "");
    }

    private String parseStoreRules(Document document) {
        return document.select("#ctl00_ContentPH_StoreCond").text().replaceAll("\\xFFFD", "");
    }

    private String parseProductManufacturer(String barcode) throws IOException {
        String manufacturerURL = "http://www.goodsmatrix.ru/goods-producer/" + barcode.trim() + ".html";

        Connection.Response manufacturerPage = Jsoup.connect(manufacturerURL)
                .method(Connection.Method.GET)
                .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2")
                .maxBodySize(0)
                .timeout(TIMEOUT)
                .execute();

        Document document = manufacturerPage.parse();

        return document.select("#ctl00_ContentPH_ManufacturerName").text().replaceAll("\\xEF", "");
    }
}
