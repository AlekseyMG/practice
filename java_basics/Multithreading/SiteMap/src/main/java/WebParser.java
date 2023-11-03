import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.*;
import java.util.concurrent.RecursiveTask;

public class WebParser extends RecursiveTask<String> {
    private final String root;

    public WebParser(String url) {
        this.root = url;
    }

    @Override
    protected String compute() {
        Set<String> links = getLinks();
        int level = root.split("/").length - 3;

        if (links.isEmpty() || root.contains(".pdf")) {
            return "\t".repeat(level) + root + "\n";
        }

        StringBuilder paths = new StringBuilder();
        List<WebParser> subTasks = new LinkedList<>();
        for (String link : links) {
            WebParser task = new WebParser(link);
            task.fork();
            subTasks.add(task);
        }
        for (WebParser task : subTasks) {
            paths.append("\t".repeat(level)).append(root).append("\n").append(task.join());
        }
        return paths.toString();
    }


    private Set<String> getLinks() {
        Set<String> links = new HashSet<>();
        if (root.contains(".pdf") || root.contains("#")) {
            return new HashSet<>();
        }
        try {
            Thread.sleep((int) (Math.random() * 200) + 200);
            System.out.println("Идем по ссылке - " + root);
            Document htmlDoc = Jsoup.connect(root).get();
            Elements htmlLinks = htmlDoc.select("a[href]");
            for (Element link : htmlLinks) {
                String url = link.attr("abs:href");
                if (url.contains(root) &&
                        !url.equals(root) &&
                        !url.contains("#") &&
                        !url.contains("@") &&
                        !url.contains(".com") &&
                        !links.contains(url)
                ) {
                    links.add(url);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return links;
    }
}
