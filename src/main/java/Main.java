import hibernate.HibernateUtil;
import org.apache.commons.lang3.SerializationUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.jsoup.select.Elements;
import parser.ParserManager;
import product.Barcode;
import product.Category;
import product.Product;
import tools.FromByteFile;
import tools.ToByteFile;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {

        ParserManager parserManager = new ParserManager(Variables.CATALOGUE_URL);

        Elements categories = parserManager.getCategoriesAsElements();
        System.out.println("Парсим категории...");

        LinkedHashMap<String, String> links = parserManager.getLinksToCategory(categories);
        System.out.println("Берем ссылки из каталагов");


        ArrayList<Category> categoryLinkedList = new ArrayList<>();

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.doReturningWork(connection -> {
            try (Statement stm = connection.createStatement()) {
                stm.execute("SET NAMES utf8mb4");
            }

            return null;
        });

        if (new File("category.dat").exists()) {
            System.out.println("Найден файл category.dat, читаем данные...");
            FromByteFile fromByteFile = new FromByteFile();

            categoryLinkedList = (ArrayList<Category>) fromByteFile.deserializeObject("category.dat");
        } else {
            for (String s : links.keySet()) {
                ArrayList<Barcode> barcodes = parserManager.parseBarcodesInCategory(links.get(s));
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

            System.out.println("Идет запись категорий в файл...");

            ToByteFile toByteFile = new ToByteFile(categoryLinkedList);
        }

        for (Category c : categoryLinkedList) {
            boolean changed = false;

            ArrayList<Barcode> barcodesAlreadyExist = new ArrayList<>();

            session.beginTransaction();

            if (c.getBarcodes().size() != 0) {
                for (Barcode b : c.getBarcodes()) {
                    Query q = session.createQuery("select count(*) from Product where BARCODE=:barcode");
                    q.setString("barcode", b.toString());

                    Long count = (Long) q.uniqueResult();

                    if (count == 0) {
                        Product p = parserManager.parseProduct(c, b);

                        try {
                            session.save(p);
                        } catch (GenericJDBCException e) {
                            e.printStackTrace();
                        }

                    } else {
                        System.out.println("Запись уже существует, пропуск...");
                        barcodesAlreadyExist.add(b);
                    }
                }
            } else {
                System.out.println("Пустая категория, пропуск");
                continue;
            }

            for (Barcode b : barcodesAlreadyExist) {
                if (!changed) changed = true;
                c.getBarcodes().remove(b);
            }

            if(changed) {
                ToByteFile toByteFile = new ToByteFile(categoryLinkedList);
            }

            session.getTransaction().commit();
        }

        HibernateUtil.shutdown();
    }
}
