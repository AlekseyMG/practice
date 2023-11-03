package fileworkers;

import fileworkers.dataclasses.StationDate;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CsvFileParser {
    private final List<StationDate> stationsData;

    public CsvFileParser() {
        this.stationsData = new ArrayList<>();
    }
    public void parse(String[] paths) {
        String csvPath = "";
        for (String path : paths) {
            if (path.contains(".csv")) {
                csvPath = path;
                break;
            }
        }
        try {
            List<String> lines = Files.readAllLines(Paths.get(csvPath));
            lines.forEach(line -> {
                if (!line.contains("name,date")) {
                    String[] data = line.split(",");
                    stationsData.add(new StationDate(data[0], data[1]));
                }
            });
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public String getDateByName(String name) {
        String date = "";
        for (StationDate station : stationsData) {
            if (station.getStationName().equals(name)) {
                date += station.getDate();
                stationsData.remove(station);
                break;
            }
        }
        return date;
    }
    public void printParsingResult() {
        System.out.println("Результат парсинга CSV:\n");
        stationsData.forEach(station ->
                System.out.println(station.getStationName() + " " + station.getDate()));
        System.out.println("------------------------------------------------------------\n");
    }
}
