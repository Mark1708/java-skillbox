package core;

public class Station {
    private Line line;
    private String name;
    private Boolean hasConnection;

    public Station(String name, Line line) {
        this.name = name;
        this.line = line;
        hasConnection = false;
    }

    public Line getLine() {
        return line;
    }

    public String getName() {
        return name;
    }

    public Boolean hasConnection() {
        return hasConnection;
    }

    public void setHasConnection(Boolean hasConnection) {
        this.hasConnection = hasConnection;
    }

    @Override
    public String toString() {
        return name;
    }
}