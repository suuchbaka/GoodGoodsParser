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

        String[] productInfo = getProductInfo(product, document);

        product.setName(productInfo[0]);
        product.setDescription(productInfo[1]);
        product.setConsist(productInfo[2]);
        product.setWeight(productInfo[3]);
        product.setExpirationDate(productInfo[4]);
        product.setEnergyConsist(productInfo[5]);
        product.setStoreRules(productInfo[6]);
        product.setManufacturer(productInfo[7]);

        return product;
    }

    private String[] getProductInfo(Product product, Document document) throws IOException {
        StringBuilder sb = new StringBuilder();


        sb
                .append(parseProductName(document))
                .append("///")
                .append(parseProductDescription(document))
                .append("///")
                .append(parseProductConsist(document))
                .append("///")
                .append(parseProductWeight(document))
                .append("///")
                .append(parseDocumentExpirationDate(document))
                .append("///")
                .append(parseEnergyConsist(document))
                .append("///")
                .append(parseStoreRules(document))
                .append("///")
                .append(parseProductManufacturer(product.getBarcode().toString()));

        return sb.toString().split("///");
    }

    private String parseProductName(Document document) {
        return document.select("#ctl00_ContentPH_GoodsName").text();
    }

    private String parseProductDescription(Document document) {
        return document.select("#ctl00_ContentPH_Comment").text();
    }

    private String parseProductConsist(Document document) {
        return document.select("ctl00_ContentPH_Composition").text();
    }

    private String parseProductWeight(Document document) {
        return document.select("#ctl00_ContentPH_Net").text();
    }

    private String parseDocumentExpirationDate(Document document) {
        return document.select("#ctl00_ContentPH_KeepingTime").text();
    }

    private String parseEnergyConsist(Document document) {
        return document.select("#ctl00_ContentPH_ESL").text();
    }

    private String parseStoreRules(Document document) {
        return document.select("#ctl00_ContentPH_StoreCond").text();
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

        return document.select("#ctl00_ContentPH_ManufacturerName").text();
    }
}
