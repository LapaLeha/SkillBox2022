import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.RecursiveTask;

public class WebAll extends RecursiveTask<List<String>> {

    private String url;
    private static CopyOnWriteArraySet<String> allLinks = new CopyOnWriteArraySet<>();

    public WebAll(String url) {
        this.url = url;
    }
    @Override
    protected List<String> compute() {
        CopyOnWriteArraySet<WebAll> subTask = new CopyOnWriteArraySet<>();
        List <String> a = new ArrayList<>();

        a.add(url);

        getChildren(subTask);

        for (WebAll link : subTask) {
            a.addAll(link.join());
        }

        return a;
    }

    private void getChildren(CopyOnWriteArraySet<WebAll> subTask) {
        Document doc;
        Elements elements;
        try {
            Thread.sleep(150);
            doc = Jsoup.connect(url).get();
            elements = doc.select("a");
            for (Element el : elements) {
                String attr = el.attr("abs:href");
                if (!attr.isEmpty() && attr.startsWith(url) && !allLinks.contains(attr) && !attr
                        .contains("#")) {
                    WebAll webAll = new WebAll(attr);
                    webAll.fork();
                    subTask.add(webAll);
                    allLinks.add(attr);
                }
            }
        } catch (InterruptedException | IOException ignored) {
        }
    }
}