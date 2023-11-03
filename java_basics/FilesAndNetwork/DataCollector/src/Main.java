import fileworkers.CsvFileParser;
import fileworkers.FileSearcher;
import fileworkers.JsonFileParser;
import fileworkers.JsonFileWriter;
import webparser.WebParser;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        String url = "https://skillbox-java.github.io/";
        String searchFolderPath = "./data";
        String outputFolderPath = "./outputdata/";

        WebParser webParser = new WebParser();
        webParser.loadHtml(url);

        webParser.printParsingResult();

        File folder = new File(searchFolderPath);
        FileSearcher fileSearcher = new FileSearcher(folder);
        ForkJoinPool pool = new ForkJoinPool();
        String[] filePaths = pool.invoke(fileSearcher).split("\n");

        System.out.println("Найдены файлы:");
        Arrays.stream(filePaths).forEach(System.out::println);
        System.out.println("------------------------------------------------------------");

        JsonFileParser jsonFileParser = new JsonFileParser();
        jsonFileParser.parse(filePaths);
        jsonFileParser.printParsingResult();

        CsvFileParser csvFileParser = new CsvFileParser();
        csvFileParser.parse(filePaths);
        csvFileParser.printParsingResult();

        JsonFileWriter jsonFileWriter = new JsonFileWriter(webParser, jsonFileParser, csvFileParser);
        jsonFileWriter.writeMap(outputFolderPath);
        jsonFileWriter.writeStations(outputFolderPath);
    }
}
