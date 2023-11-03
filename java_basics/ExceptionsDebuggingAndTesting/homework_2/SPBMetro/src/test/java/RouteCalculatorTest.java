import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase {
    StationIndex stationIndex;

    @Override
    protected void setUp() throws Exception {
        stationIndex = new StationIndex();

        stationIndex.addLine(new Line(1, "1я - Красная"));
        stationIndex.addLine(new Line(2, "2я - Синяя"));
        stationIndex.addLine(new Line(3, "3я - Зеленая"));
        stationIndex.addLine(new Line(4, "4я - Желтая"));
        stationIndex.addLine(new Line(5, "5я - Фиолетовая"));

        //============ 1я - Красная ==========
        ArrayList<Station> stationsArray = new ArrayList<>();
        stationsArray.add(new Station("Площадь Ленина", stationIndex.getLine(1)));
        stationsArray.add(new Station("Чернышевская", stationIndex.getLine(1)));
        stationsArray.add(new Station("Площадь Восстания", stationIndex.getLine(1)));
        stationsArray.add(new Station("Владимирская", stationIndex.getLine(1)));
        stationsArray.add(new Station("Пушкинская", stationIndex.getLine(1)));
        stationsArray.add(new Station("Технологический институт", stationIndex.getLine(1)));
        stationsArray.add(new Station("Балтийская", stationIndex.getLine(1)));
        stationsArray.add(new Station("Нарвская", stationIndex.getLine(1)));

        addStations(stationsArray, 1);

        //============ 2я - Синяя ============
        stationsArray = new ArrayList<>();
        stationsArray.add(new Station("Петроградская", stationIndex.getLine(2)));
        stationsArray.add(new Station("Горьковская", stationIndex.getLine(2)));
        stationsArray.add(new Station("Невский проспект", stationIndex.getLine(2)));
        stationsArray.add(new Station("Сенная площадь", stationIndex.getLine(2)));
        stationsArray.add(new Station("Технологический институт", stationIndex.getLine(2)));
        stationsArray.add(new Station("Фрунзенская", stationIndex.getLine(2)));
        stationsArray.add(new Station("Московские ворота", stationIndex.getLine(2)));

        addStations(stationsArray, 2);

        //=========== 3я - Зеленая ===========
        stationsArray = new ArrayList<>();
        stationsArray.add(new Station("Приморская", stationIndex.getLine(3)));
        stationsArray.add(new Station("Василеостровская", stationIndex.getLine(3)));
        stationsArray.add(new Station("Гостиный двор", stationIndex.getLine(3)));
        stationsArray.add(new Station("Маяковская", stationIndex.getLine(3)));
        stationsArray.add(new Station("Площадь Александра Невского", stationIndex.getLine(3)));
        stationsArray.add(new Station("Елизаровская", stationIndex.getLine(3)));
        stationsArray.add(new Station("Ломоносовская", stationIndex.getLine(3)));

        addStations(stationsArray, 3);

        //=========== 4я - Желтая ============
        stationsArray = new ArrayList<>();
        stationsArray.add(new Station("Спасская", stationIndex.getLine(4)));
        stationsArray.add(new Station("Достоевская", stationIndex.getLine(4)));
        stationsArray.add(new Station("Лиговский проспект", stationIndex.getLine(4)));
        stationsArray.add(new Station("Площадь Александра Невского", stationIndex.getLine(4)));
        stationsArray.add(new Station("Новочеркасская", stationIndex.getLine(4)));
        stationsArray.add(new Station("Ладожская", stationIndex.getLine(4)));

        addStations(stationsArray, 4);

        //=========== 5я - Фиолетовая ===========
        stationsArray = new ArrayList<>();
        stationsArray.add(new Station("Чкаловская", stationIndex.getLine(5)));
        stationsArray.add(new Station("Спортивная", stationIndex.getLine(5)));
        stationsArray.add(new Station("Адмиралтейская", stationIndex.getLine(5)));
        stationsArray.add(new Station("Садовая", stationIndex.getLine(5)));
        stationsArray.add(new Station("Звенигородская", stationIndex.getLine(5)));
        stationsArray.add(new Station("Обводный канал", stationIndex.getLine(5)));
        stationsArray.add(new Station("Волковская", stationIndex.getLine(5)));

        addStations(stationsArray, 5);

        stationIndex.addConnection(List.of(
                new Station("Невский проспект", stationIndex.getLine(2)),
                new Station("Гостиный двор", stationIndex.getLine(3))));
        stationIndex.addConnection(List.of(
                new Station("Площадь Восстания", stationIndex.getLine(1)),
                new Station("Маяковская", stationIndex.getLine(3))));
        stationIndex.addConnection(List.of(
                new Station("Сенная Площадь", stationIndex.getLine(2)),
                new Station("Спасская", stationIndex.getLine(4)),
                new Station("Садовая", stationIndex.getLine(5))));
        stationIndex.addConnection(List.of(
                new Station("Владимирская", stationIndex.getLine(1)),
                new Station("Достоевская", stationIndex.getLine(4))));
        stationIndex.addConnection(List.of(
                new Station("Пушкинская", stationIndex.getLine(1)),
                new Station("Звенигородская", stationIndex.getLine(5))));
        stationIndex.addConnection(List.of(
                new Station("Площадь Александра Невского", stationIndex.getLine(3)),
                new Station("Площадь Александра Невского", stationIndex.getLine(4))));
        stationIndex.addConnection(List.of(
                new Station("Технологический институт", stationIndex.getLine(1)),
                new Station("Технологический институт", stationIndex.getLine(2))
                ));
    }
    public void addStations(ArrayList<Station> stations, int lineNumber) {
        Line line = stationIndex.getLine(lineNumber);
        stations.forEach(station -> {
            stationIndex.addStation(station);
            line.addStation(station);
        });
    }
    public List<Station> getShortestRoute(String from, String to) {
        RouteCalculator calculator = new RouteCalculator(stationIndex);
        List<Station> route = calculator.getShortestRoute(
                stationIndex.getStation(from),
                stationIndex.getStation(to)
        );
        return route;
    }
    public void testCalculateDurationWithoutConnection1() {
        double actual = RouteCalculator.calculateDuration(getShortestRoute("Василеостровская", "Елизаровская"));
        double expected = 10.0;
        assertEquals(expected, actual);
    }
    public void testCalculateDurationWithoutConnection2() {
        double actual = RouteCalculator.calculateDuration(getShortestRoute("Балтийская", "Чернышевская"));
        double expected = 12.5;
        assertEquals(expected, actual);
    }
    public void testCalculateDurationWithOneConnection1() {
        double actual = RouteCalculator.calculateDuration(getShortestRoute("Василеостровская", "Петроградская"));
        double expected = 11.0;
        assertEquals(expected, actual);
    }
    public void testCalculateDurationWithOneConnection2() {
        double actual = RouteCalculator.calculateDuration(getShortestRoute("Балтийская", "Ладожская"));
        double expected = 21.0;
        assertEquals(expected, actual);
    }
    public void testCalculateDurationWithOneConnection3() {
        double actual = RouteCalculator.calculateDuration(getShortestRoute("Волковская", "Чернышевская"));
        double expected = 16.0;
        assertEquals(expected, actual);
    }
    public void testCalculateDurationWithTwoConnections1() {
        double actual = RouteCalculator.calculateDuration(getShortestRoute("Спортивная", "Елизаровская"));
        double expected = 22.0;
        assertEquals(expected, actual);
    }
    public void testCalculateDurationWithTwoConnections2() {
        double actual = RouteCalculator.calculateDuration(getShortestRoute("Василеостровская", "Обводный канал"));
        double expected = 17.0;
        assertEquals(expected, actual);
    }
    public void testGetShortestRouteWithoutConnection1() {
        List<Station> actual = getShortestRoute("Василеостровская", "Елизаровская");
        List<Station> expected = List.of(
                stationIndex.getStation("Василеостровская"),
                stationIndex.getStation("Гостиный двор"),
                stationIndex.getStation("Маяковская"),
                stationIndex.getStation("Площадь Александра Невского"),
                stationIndex.getStation("Елизаровская")
        );
        assertEquals(expected, actual);
    }
    public void testGetShortestRouteWithoutConnection2() {
        List<Station> actual = getShortestRoute("Балтийская", "Чернышевская");
        List<Station> expected = List.of(
                stationIndex.getStation("Балтийская"),
                stationIndex.getStation("Технологический институт"),
                stationIndex.getStation("Пушкинская"),
                stationIndex.getStation("Владимирская"),
                stationIndex.getStation("Площадь Восстания"),
                stationIndex.getStation("Чернышевская")
                );
        assertEquals(expected, actual);
    }
    public void testGetShortestRouteWithOneConnection1() {
        List<Station> actual = getShortestRoute("Василеостровская", "Петроградская");
        List<Station> expected = List.of(
                stationIndex.getStation("Василеостровская"),
                stationIndex.getStation("Гостиный двор"),
                stationIndex.getStation("Невский проспект"),
                stationIndex.getStation("Горьковская"),
                stationIndex.getStation("Петроградская")
                );
        assertEquals(expected, actual);
    }
    public void testGetShortestRouteWithOneConnection2() {
        List<Station> actual = getShortestRoute("Балтийская", "Ладожская");
        List<Station> expected = List.of(
                stationIndex.getStation("Балтийская"),
                stationIndex.getStation("Технологический институт", 1),
                stationIndex.getStation("Пушкинская"),
                stationIndex.getStation("Владимирская"),
                stationIndex.getStation("Достоевская"),
                stationIndex.getStation("Лиговский проспект"),
                stationIndex.getStation("Площадь Александра Невского", 4),
                stationIndex.getStation("Новочеркасская"),
                stationIndex.getStation("Ладожская")
                );
        assertEquals(expected, actual);
    }
    public void testGetShortestRouteWithOneConnection3() {
        List<Station> actual = getShortestRoute("Волковская", "Чернышевская");
        List<Station> expected = List.of(
                stationIndex.getStation("Волковская"),
                stationIndex.getStation("Обводный канал"),
                stationIndex.getStation("Звенигородская"),
                stationIndex.getStation("Пушкинская"),
                stationIndex.getStation("Владимирская"),
                stationIndex.getStation("Площадь Восстания"),
                stationIndex.getStation("Чернышевская")
                );
        assertEquals(expected, actual);
    }
    public void testGetShortestRouteWithTwoConnections1() {
        List<Station> actual = getShortestRoute("Спортивная", "Елизаровская");
        List<Station> expected = List.of(
                stationIndex.getStation("Спортивная"),
                stationIndex.getStation("Адмиралтейская"),
                stationIndex.getStation("Садовая"),
                stationIndex.getStation("Сенная площадь"),
                stationIndex.getStation("Невский проспект"),
                stationIndex.getStation("Гостиный двор"),
                stationIndex.getStation("Маяковская"),
                stationIndex.getStation("Площадь Александра Невского"),
                stationIndex.getStation("Елизаровская")
                );
        assertEquals(expected, actual);
    }
    public void testGetShortestRouteWithTwoConnections2() {
        List<Station> actual = getShortestRoute("Василеостровская", "Обводный канал");
        List<Station> expected = List.of(
                stationIndex.getStation("Василеостровская"),
                stationIndex.getStation("Гостиный двор"),
                stationIndex.getStation("Невский проспект"),
                stationIndex.getStation("Сенная площадь"),
                stationIndex.getStation("Садовая"),
                stationIndex.getStation("Звенигородская"),
                stationIndex.getStation("Обводный канал")
                );
        assertEquals(expected, actual);
    }
}
