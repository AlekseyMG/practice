package fileworkers.dataclasses;

public class StationDate {
    private final String stationName;
    private final String date;

    public StationDate(String stationName, String date) {
        this.stationName = stationName;
        this.date = date;
    }
    public String getStationName() {
        return stationName;
    }
    public String getDate() {
        return date;
    }
}
