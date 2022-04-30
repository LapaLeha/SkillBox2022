package main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;

public class Main {

    private static org.jsoup.nodes.Document getPage() throws IOException {
        String url = "https://lenta.ru/";
        Document page = Jsoup.parse(new URL(url), 3000);
        return page;
    }

    public static void main(String[] args) throws IOException {
        Elements elements = getPage().select("img");
        for (Element element : elements) {
            try {
                URL url = new URL(element.absUrl("src"));
                InputStream inputStream = url.openStream();
                String nameImages = element.absUrl("src").replaceAll(".+/(.+\\.\\w{3,4})", "$1");
                String path = "data/images/" + nameImages;
                if (element.absUrl("src").matches(".+/(.+\\.\\w{3,4})")) {
                    System.out.println(nameImages);
                    Files.copy(inputStream, new File(path).toPath());
                }
            } catch (FileAlreadyExistsException ex) {
                ex.getStackTrace();
            }
        }
    }
}
