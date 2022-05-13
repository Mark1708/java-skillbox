import core.Line;
import core.Station;

import java.util.*;
import java.util.stream.Collectors;

public class StationIndex {
    HashMap<String, Line> number2line;
    HashSet<Station> stations;
    HashMap<Station, HashSet<Station>> connections;

    public StationIndex() {
        number2line = new HashMap<>();
        stations = new HashSet<>();
        connections = new HashMap<Station, HashSet<Station>>();
    }

    public void addStation(Station station) {
        stations.add(station);
    }

    public void addLine(Line line) {
        number2line.put(line.getNumber(), line);
    }

    public void addConnection(List<Station> stations) {
        for (Station station : stations) {
            if (!connections.containsKey(station)) {
                connections.put(station, new HashSet<Station>());
            }
            HashSet<Station> connectedStations = connections.get(station);
            connectedStations.addAll(stations.stream()
                    .filter(s -> !s.equals(station)).collect(Collectors.toList()));
        }
    }

    public Line getLine(String number) {
        return number2line.get(number);
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
        return stations.stream().filter(station -> station.getName().equals(name) && station.getLine().getNumber().equals(lineNumber)).collect(Collectors.toList()).get(0);
    }

    public HashSet<Station> getConnectedStations(Station station) {
        if (connections.containsKey(station)) {
            return connections.get(station);
        }
        return new HashSet<>();
    }
}
