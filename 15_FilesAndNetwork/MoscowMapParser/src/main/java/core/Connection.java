package core;


import java.util.ArrayList;
import java.util.HashSet;

public class Connection {

    HashSet<Station> connections;

    public Connection() {
        this.connections = new HashSet<>();
    }

    public HashSet<Station> getConnections() {
        return connections;
    }


    public ArrayList<String> getNameStations() {
        ArrayList<String> names = new ArrayList<>();
        connections.forEach(connection -> names.add(connection.getName()));
        return names;
    }


    public void addStation(Station station) {
        connections.add(station);
    }
}
