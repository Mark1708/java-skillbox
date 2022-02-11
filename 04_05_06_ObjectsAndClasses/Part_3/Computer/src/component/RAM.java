package component;

public class RAM {
    /*
     *Оперативная память:
        тип,
        объём,
        вес.
     */
    private final String type;
    private final double volume;
    private final double weight;

    public RAM(String type, double volume, double weight) {
        this.type = type;
        this.volume = volume;
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public double getVolume() {
        return volume;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "RAM{" +
                "type='" + type + '\'' +
                ", volume=" + volume +
                ", weight=" + weight +
                '}';
    }
}
