import java.util.*;

public class MetroMsk {
    private final HashMap <String, Line> number2line;
    private final TreeSet<Station> stations;
    //private final Map<Station, TreeSet<Station>> connections;

    public MetroMsk() {
        number2line = new HashMap<>();
        stations = new TreeSet<>();
        //connections = new TreeMap<>();
    }

    public void addStation(Station station) {
        stations.add(station);
    }

    public void addLine(Line line) {
        number2line.put(line.getNumber(), line);
    }

/*    public void addConnection(List<Station> stations) {
        for (Station station : stations) {
            if (!connections.containsKey(station)) {
                connections.put(station, new TreeSet<>());
            }
            TreeSet<Station> connectedStations = connections.get(station);
            connectedStations.addAll(stations.stream()
                    .filter(s -> !s.equals(station)).collect(Collectors.toList()));
        }
    }*/

    public Line getLine(String number) {
        return number2line.get(number);
    }
    public List<Line> getLines () {
        List <Line> l = new ArrayList<>();
        for (Map.Entry<String, Line> entry : number2line .entrySet()) {
            l.add(entry.getValue());
        }
        return l;
    }

    public Station getStation(String name) {
        for (Station station : stations) {
            if (station.getName().equalsIgnoreCase(name)) {
                return station;
            }
        }
        return null;
    }

    public Station getStation(String name, String lineNumber) {
        Station query = new Station(name, getLine(lineNumber));
        Station station = stations.ceiling(query);
        return station.equals(query) ? station : null;
    }

 /*   public Set<Station> getConnectedStations(Station station) {
        return connections.containsKey(station) ?
                connections.get(station) : new TreeSet<>();
    }*/
}

