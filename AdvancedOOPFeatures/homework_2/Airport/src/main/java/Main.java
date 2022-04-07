import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Airport airport=Airport.getInstance();
        airport.getAllAircrafts();
        for (Flight f:findPlanesLeavingInTheNextTwoHours(airport)){
            System.out.println(f);
        }
    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {

       List<Flight> flights=new ArrayList<>();
        Date date=new Date();
        long d = date.getHours();
        airport.getTerminals().forEach(terminal -> terminal.getFlights().stream()
                .filter(flight -> (flight.getType()== Flight.Type.ARRIVAL)&&(flight.getDate().getHours()>=(d-2))));

        for (Terminal t:airport.getTerminals()){
            for (Flight flight:t.getFlights()){
                if (flight.getType()== Flight.Type.DEPARTURE){
                    if (flight.getDate().getHours()>=d-2){
                        flights.add(flight);
                    }
                }
            }
        }
        return flights;
    }
}