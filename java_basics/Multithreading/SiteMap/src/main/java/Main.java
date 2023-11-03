import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://skillbox.ru/";
        String outputFolderPath = "./data/";
        String outputFile = "sitemap.txt";

        WebParser webParser = new WebParser(url);
        ForkJoinPool pool = new ForkJoinPool();
        String links = pool.invoke(webParser);
        Set<String> linksSet = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.trim().compareTo(s2.trim());
            }
        });
        linksSet.addAll(Arrays.asList(links.split("\n")));
        linksSet.forEach(System.out::println);

        StringBuilder stringBuilder = new StringBuilder();
        linksSet.forEach(s -> stringBuilder.append(s).append("\n"));

        Path folder = Paths.get(outputFolderPath);
        Path filePath = Paths.get(outputFolderPath + outputFile);
        if (!Files.exists(folder)) {
            Files.createDirectory(folder);
        }
        Files.writeString(filePath, stringBuilder.toString());
    }
}