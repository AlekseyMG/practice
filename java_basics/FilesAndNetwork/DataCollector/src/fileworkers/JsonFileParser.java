package fileworkers;

import fileworkers.dataclasses.StationDepth;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;
import java.nio.file.Paths;

public class JsonFileParser {
    private final List<StationDepth> stationsData;
    public JsonFileParser() {
        this.stationsData = new ArrayList<>();
    }
    public void parse(String[] paths) {
        String jsonPath = "";
        for (String path : paths) {
            if (path.contains(".json")) {
                jsonPath = path;
                break;
            }
        }
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            stationsData.addAll(Arrays.asList
                    (mapper.readValue(Paths.get(jsonPath).toFile(), StationDepth[].class)));
        }
        catch (Exception ex)
        {
            throw new RuntimeException("Ошибка парсинга json: " + jsonPath, ex);
        }
    }
    public Double getDepthByName(String name) {
        Double depth = null;
        for (StationDepth stationDepth : stationsData) {
            if (stationDepth.getStationName().equals(name)
            && !stationDepth.getDepth().equals("?")) {
                depth = Double.parseDouble(stationDepth
                        .getDepth()
                        .replaceAll(",", ".")
                );
                stationsData.remove(stationDepth);
                break;
            }
        }
        return depth;
    }
    public void printParsingResult() {
        System.out.println("Результат парсинга JSON:\n");
        stationsData.forEach(station ->
                System.out.println(station.getStationName() + " " + station.getDepth()));
        System.out.println("------------------------------------------------------------\n");
    }
}
