import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase {
    List<Station> route;

    private StationIndex stationIndex;
    private RouteCalculator calculator;


    @Override
    protected void setUp() throws Exception {
        stationIndex = new StationIndex();
        calculator = new RouteCalculator(stationIndex);

        route = new ArrayList<>();
        Line line1 = new Line(1, "blue");
        Line line2 = new Line(2, "green");
        Line line3 = new Line(3, "yellow");

        Station stationA1 = new Station("A1", line1);
        Station stationA2 = new Station("A2", line1);
        Station stationB1 = new Station("B1", line2);
        Station stationB2 = new Station("B2", line2);
        Station stationB3 = new Station("B3", line2);
        Station stationB4 = new Station("B4", line2);
        Station stationC1 = new Station("C1", line3);
        Station stationC2 = new Station("C2", line3);

        line1.addStation(stationA1);
        line1.addStation(stationA2);
        line2.addStation(stationB1);
        line2.addStation(stationB2);
        line2.addStation(stationB3);
        line2.addStation(stationB4);
        line3.addStation(stationC1);
        line3.addStation(stationC2);

        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);
        stationIndex.addStation(stationA1);
        stationIndex.addStation(stationA2);
        stationIndex.addStation(stationB1);
        stationIndex.addStation(stationB2);
        stationIndex.addStation(stationB3);
        stationIndex.addStation(stationB4);
        stationIndex.addStation(stationC1);
        stationIndex.addStation(stationC2);

        List<Station> connection = new ArrayList<>();
        connection.add(stationA2);
        connection.add(stationB2);

        List<Station> connection2 = new ArrayList<>();
        connection2.add(stationC2);
        connection2.add(stationB4);

        stationIndex.addConnection(connection);
        stationIndex.addConnection(connection2);


        route.add(new Station("A1", line1));
        route.add(new Station("A2", line1));
        route.add(new Station("B2", line2));
        route.add(new Station("B3", line2));
        route.add(new Station("B4", line2));
        route.add(new Station("C2", line3));
        route.add(new Station("C1", line3));
    }

    public void testGetShortestRouteOnTheLine(){
        List<Station>actual=calculator.getShortestRoute(stationIndex
                .getStation("B1"),stationIndex.getStation("B4"));
        List <Station> exspected = new ArrayList<>();
        exspected.add(stationIndex.getStation("B1"));
        exspected.add(stationIndex.getStation("B2"));
        exspected.add(stationIndex.getStation("B3"));
        exspected.add(stationIndex.getStation("B4"));
        assertEquals(exspected,actual);
    }

    public void testGetShortestRouteWithOneConnection(){
        List<Station>actual=calculator.getShortestRoute(stationIndex
                .getStation("A1"),stationIndex.getStation("B3"));
        List <Station> exspected = new ArrayList<>();
        exspected.add(stationIndex.getStation("A1"));
        exspected.add(stationIndex.getStation("A2"));
        exspected.add(stationIndex.getStation("B2"));
        exspected.add(stationIndex.getStation("B3"));
        assertEquals(exspected,actual);
    }

    public void testGetShortestRouteWithTwoConnections(){
        List<Station>actual=calculator.getShortestRoute(stationIndex
                .getStation("A1"),stationIndex.getStation("C1"));
        List <Station> exspected = new ArrayList<>();
        exspected.add(stationIndex.getStation("A1"));
        exspected.add(stationIndex.getStation("A2"));
        exspected.add(stationIndex.getStation("B2"));
        exspected.add(stationIndex.getStation("B3"));
        exspected.add(stationIndex.getStation("B4"));
        exspected.add(stationIndex.getStation("C2"));
        exspected.add(stationIndex.getStation("C1"));
        assertEquals(exspected,actual);
    }

    public void testCalculateDuration() {
        double actual = RouteCalculator.calculateDuration(route);
        double exspected = 17.0;
        assertEquals(exspected, actual);
    }

    @Override
    protected void tearDown() throws Exception {

    }
}
