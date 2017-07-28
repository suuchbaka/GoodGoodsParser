package parser;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import product.Barcode;

import java.io.IOException;
import java.util.ArrayList;
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
                .userAgent("Mozilla")
                .timeout(TIMEOUT)
                .execute();

        Document document = productGroups.parse();

        Elements catalogueTable = document.select("#ctl00_ContentPH_GroupsDG.Grd");

        return catalogueTable.select("a[href]");
    }

    public LinkedList<Barcode> getAllBarcodesByCategory(String categoryUrl) throws IOException {
        LinkedList<Barcode> barcodes = new LinkedList<>();

        Connection.Response categoryPage = Jsoup.connect(categoryUrl)
                .method(Connection.Method.GET)
                .userAgent("Mozilla")
                .timeout(TIMEOUT)
                .execute();

        Document document = categoryPage.parse();

        int totalBarcodesOnPage = countTotalBarcodesOnPage(document);

        for(int i = 3; i < totalBarcodesOnPage; i++) {
            if(i <= 9) {
                barcodes.add(new Barcode(Long.valueOf(document.select("#ctl00_ContentPH_GoodsDG_ctl0" + i + "_A2").text().trim())));
            }

            else {
                barcodes.add(new Barcode(Long.valueOf(document.select("#ctl00_ContentPH_GoodsDG_ctl" + i + "_A2").text().trim())));
            }
        }

        return barcodes;
    }

    private int countTotalBarcodesOnPage(Document document) throws IOException {
        int index = 3;
        int totalBarcodesCtl = index;
        int ctlIndex = 0;

        boolean haveBarcode = true;

        while(haveBarcode) {
            totalBarcodesCtl++;
            index++;

            if(index > 9) {
                ctlIndex++;
                index = 0;
                totalBarcodesCtl = index;
            }

            StringBuilder sb = new StringBuilder();

            sb
                    .append("#ctl00_ContentPH_GoodsDG_ctl")
                    .append(ctlIndex)
                    .append(totalBarcodesCtl)
                    .append("_A2");

            haveBarcode = document.select(sb.toString()).size() != 0;
        }

        StringBuilder sb = new StringBuilder();

        sb
                .append(ctlIndex)
                .append(totalBarcodesCtl);


        return Integer.valueOf(sb.toString());
    }

    public String getLinkToProductGroupAsString(Element catalogueElement) {
        if(catalogueElement.attr("href").contains("http")) {
            return catalogueElement.attr("href").trim();
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
