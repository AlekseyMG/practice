package fileworkers.dataclasses;

import com.fasterxml.jackson.annotation.JsonSetter;

public class StationDepth {
    @JsonSetter("station_name")
    private String stationName;
    @JsonSetter("depth")
    private String depth;

    public StationDepth() {
        this.stationName = "";
        this.depth = "";
    }
    public String getStationName() {
        return stationName;
    }
    public String getDepth() {
        return depth;
    }
}
