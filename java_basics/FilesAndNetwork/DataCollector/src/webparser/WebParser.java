package webparser;

import webparser.dataclasses.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class WebParser {
    private final Map<Integer, Line> number2line;
    private final TreeSet<Station> stations;
    private final Map<Station, TreeSet<Station>> connections;

    public WebParser() {
        number2line = new HashMap<>();
        stations = new TreeSet<>();
        connections = new TreeMap<>();
    }
    public void loadHtml(String path) {
//получение HTML-кода страницы «Список станций Московского метрополитена» с помощью библиотеки jsoup;
        try {
            Document htmlDoc = Jsoup.connect(path).get();
            parseHtmlData(htmlDoc);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void parseHtmlData(Document htmlDoc) {
//парсинг полученной страницы и получение из неё следующих данных
//(создайте для каждого типа данных отдельные классы):
// - линии московского метро (имя и номер линии, цвет не нужен);
// - станции московского метро (имя станции и номер линии).
        Elements metroData = htmlDoc.select("div#metrodata");
        Elements metroLines = metroData.select("span.js-metro-line");

        addAllLines(metroLines);

        Elements metroStations = metroData.select("div.js-metro-stations");
        AtomicInteger i = new AtomicInteger(-1);
        metroStations.forEach(line -> {
                    i.getAndIncrement();
                    line.select("p").forEach(station -> {
                                String stationName = station
                                        .text()
                                        .replaceAll("[0-9]*\\. ", "");
                                Line currentLine = getLine(i.get());
                                Station currentStation = new Station(stationName, currentLine);
                                currentLine.addStation(currentStation);
                                addStation(currentStation);
                                setConnections(station, i);
                    });
        });
    }
    public void addStation(Station station) {
        stations.add(station);
    }
    public void addLine(Line line) {
        number2line.put(line.getNumber(), line);
    }
    private void addAllLines(Elements lines) {
        int i = 0;
        for (Element line : lines) {
            addLine(new Line(i, line.text()));
            i++;
        }
    }
    private void setConnections(Element station, AtomicInteger lineNum) {
        List<Station> stationsList = new ArrayList<>();
        String currentStation = station.text().replaceAll("[0-9]*\\. ", "");
        stationsList.add(new Station(currentStation, getLine(lineNum.get())));
        station.select("span[title]").forEach(connection -> {
                    String connectionName = getLineNameFromConnection(connection.toString());
                    int connectionNum = getLineNumFromConnection(connection.toString());
                    stationsList.add(new Station(connectionName, getLine(connectionNum)));
        });
        addConnection(stationsList);
    }
    private String getLineNameFromConnection(String connection) {
        return connection.substring(connection.indexOf('«') + 1, connection.indexOf('»'));
    }
    private int getLineNumFromConnection(String connection) {
        connection = connection.replaceAll("12", "13")
                .replaceAll("11A", "12")
                .replaceAll("D1", "16")
                .replaceAll("D2", "17")
                .replaceAll("[^0-9]*", "");
        if (connection.matches("[0-9]*")) {
            return Integer.parseInt(connection) - 1;
        }
        return -1;
    }
    public void addConnection(List<Station> stations) {
        for (Station station : stations) {
            if (!connections.containsKey(station)) {
                connections.put(station, new TreeSet<>());
            }
            TreeSet<Station> connectedStations = connections.get(station);
            connectedStations.addAll(stations.stream()
                    .filter(s -> !s.equals(station)).toList());
        }
    }
    public Line getLine(int number) {
        return number2line.get(number);
    }
    public Set<Station> getConnectedStations(Station station) {
        return connections.containsKey(station) ?
                connections.get(station) : new TreeSet<>();
    }
    public void printParsingResult() {
        String delimiter = "\t---------------------------------------------------------------------------";
        System.out.println("\nРезультат парсинга Web страницы:");
        number2line.forEach((integer, line) -> {
            System.out.println(delimiter);
            System.out.println("Линия - " + (line.getNumber() + 1) + ". " + line.getName());
            line.getStations().forEach(station -> {
                    System.out.println(delimiter);
                    System.out.println("\t" + station + " -> ");
                    getConnectedStations(station).forEach(connectedStation ->
                            System.out.println("\t\t\t\t переход на " + connectedStation));
            });
    });
        System.out.println("------------------------------------------------------------\n");
    }
    public Map<Integer, Line> getNumber2line() {
        return number2line;
    }
    public Map<Station, TreeSet<Station>> getConnections() {
        return connections;
    }
}
