import com.skillbox.airport.Aircraft;
import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        findPlanesLeavingInTheNextTwoHours(Airport.getInstance());

    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        //TODO Метод должден вернуть список рейсов вылетающих в ближайшие два часа.
        Date startTime = new Date(System.currentTimeMillis());
        Date endTime = new Date(System.currentTimeMillis() + 7_200_000);
        List<Flight> flightList = new ArrayList<>();

        for (Terminal terminal : airport.getTerminals()) {
            flightList.addAll(terminal.getFlights()
                    .stream()
                    .filter(flight -> flight.getType().equals(Flight.Type.DEPARTURE))
                    .filter(flight -> flight.getDate().after(startTime))
                    .filter(flight -> flight.getDate().before(endTime))
                    .collect(Collectors.toList()));
        }
        return flightList;
    }
}