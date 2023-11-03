package fileworkers;

import webparser.WebParser;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonFileWriter {

    private final JsonObjectsBuilder jsonObjectsBuilder;

    public JsonFileWriter(WebParser webParser, JsonFileParser jsonFileParser, CsvFileParser csvFileParser) {
        this.jsonObjectsBuilder = new JsonObjectsBuilder(webParser, jsonFileParser, csvFileParser);
    }
    public void writeMap(String folderPath) {
        writeFile(folderPath, "map.json", createJsonMap());
    }
    public void writeStations(String folderPath) {
        writeFile(folderPath, "stations.json", createJsonStations());
    }
    private void writeFile(String folderPath, String fileName, String data) {
        try {
            Path filePath = Paths.get(folderPath + fileName);
            Path folder = Paths.get(folderPath);
            if (!Files.exists(folder)) {
                Files.createDirectory(folder);
            }
            Files.writeString(filePath, data);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Создан файл: " + folderPath + fileName);
    }
    private String createJsonMap() {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("stations", jsonObjectsBuilder.getStationsList());
        linkedHashMap.put("connections", jsonObjectsBuilder.getConnectionsList());
        linkedHashMap.put("lines", jsonObjectsBuilder.getLinesList());

        return createJsonString(linkedHashMap);
    }
    private String createJsonStations() {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("stations", jsonObjectsBuilder.getStationsListWithParameters());

        return createJsonString(linkedHashMap);
    }
    private String createJsonString(LinkedHashMap<String, Object> linkedHashMap) {
        String json = "";
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
        printer.indentArraysWith(new DefaultIndenter());

        JsonMapper mapper = JsonMapper.builder()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .defaultPrettyPrinter(printer)
                .build();

        try {
            json = mapper.writeValueAsString(linkedHashMap);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return json;
    }
}
