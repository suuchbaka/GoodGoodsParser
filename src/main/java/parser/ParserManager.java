package parser;

/**
 * Created by User on 28.07.2017.
 */
public class ParserManager {

    public ParserManager(String catalogueURL) {
        CategoriesParser categoriesParser = new CategoriesParser(catalogueURL);
    }

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
