package parser;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

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

    public String getLinkToProductGroupAsString(Element catalogueElement) {
        return catalogueElement.attr("href");
    }
}
