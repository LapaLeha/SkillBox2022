import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Airport airport=Airport.getInstance();
        airport.getAllAircrafts();
        System.out.println(findPlanesLeavingInTheNextTwoHours(airport));
    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {

       List<Flight> flights=new ArrayList<>();
       LocalDateTime now = LocalDateTime.now();
        airport.getTerminals().forEach(t ->t.getFlights().stream()
                .filter(flight -> (flight.getType()== Flight.Type.DEPARTURE))
                .filter(flight -> localDateTime(flight.getDate()).getHour()<=(now.plusHours(2).getHour()))
                .filter(flight -> localDateTime(flight.getDate()).getHour()>=(now.minusHours(2).getHour()))
                .forEach(flights::add));

        return flights;
    }
    public static LocalDateTime localDateTime(Date date){
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}