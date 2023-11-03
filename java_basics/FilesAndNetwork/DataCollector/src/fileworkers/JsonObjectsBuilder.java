package fileworkers;

import webparser.WebParser;
import java.util.*;

public class JsonObjectsBuilder {
    private final WebParser webParser;
    private final JsonFileParser jsonFileParser;
    private final CsvFileParser csvFileParser;

    public JsonObjectsBuilder(WebParser webParser, JsonFileParser jsonFileParser, CsvFileParser csvFileParser) {
        this.webParser = webParser;
        this.jsonFileParser = jsonFileParser;
        this.csvFileParser = csvFileParser;
    }
    public Map<Integer, List<String>> getStationsList() {
        Map<Integer, List<String>> map = new HashMap<>();

        webParser.getNumber2line().forEach((integer, line) -> {
            List<String> array = new ArrayList<>();
            line.getStations().forEach(station -> array.add(station.getName()));
            map.put(integer + 1, array);
        });
        return map;
    }
    public List<List<Map<String, Object>>> getConnectionsList() {
        List<List<Map<String, Object>>> allConnectedStationsArray = new ArrayList<>();

        webParser.getConnections().forEach((station, stations) -> {
            if (!stations.isEmpty()){
                Map<String, Object> currentStationMap = new LinkedHashMap<>();
                List<Map<String, Object>> connectedStationsArray = new ArrayList<>();

                currentStationMap.put("line", station.getLine().getNumber() + 1);
                currentStationMap.put("station", station.getName());
                connectedStationsArray.add(currentStationMap);

                stations.forEach(connection -> {
                    Map<String, Object> connectedStationMap = new LinkedHashMap<>();

                    connectedStationMap.put("line", connection.getLine().getNumber() + 1);
                    connectedStationMap.put("station", connection.getName());
                    connectedStationsArray.add(connectedStationMap);
                });

                if (!allConnectedStationsArray.toString()
                        .contains(connectedStationsArray.get(0).toString())) {
                    allConnectedStationsArray.add(connectedStationsArray);
                }
            }
        });
        return allConnectedStationsArray;
    }
    public List<Map<String, Object>> getLinesList() {
        List<Map<String, Object>> linesArray = new ArrayList<>();

        webParser.getNumber2line().forEach((integer, line) -> {
            Map<String, Object> lineMap = new LinkedHashMap<>();
            lineMap.put("number", integer + 1);
            lineMap.put("name", line.getName());
            linesArray.add(lineMap);
        });
        return linesArray;
    }
    public List<Map<String, Object>> getStationsListWithParameters () {
        List<Map<String, Object>> stationsArray = new ArrayList<>();

        webParser.getNumber2line().forEach((integer, line) -> {
            line.getStations().forEach(station -> {
                boolean hasConnection = !webParser.getConnectedStations(station).isEmpty();
                Map<String, Object> stationMap = new LinkedHashMap<>();
                String date = csvFileParser.getDateByName(station.getName());
                Double depth = jsonFileParser.getDepthByName(station.getName());

                stationMap.put("name", station.getName());
                stationMap.put("line", line.getName());

                if (!date.isEmpty()) {
                    stationMap.put("date", date);
                }

                if (depth != null && depth * 10 % 10 == 0) {
                    stationMap.put("depth", Integer.parseInt(depth
                            .toString()
                            .replace(".0", "")
                    ));
                } else if (depth != null) {
                    stationMap.put("depth", depth);
                }

                stationMap.put("hasConnection", hasConnection);

                if (!stationsArray.contains(stationMap)) {
                    stationsArray.add(stationMap);
                }
            });
        });
        return stationsArray;
    }
}
