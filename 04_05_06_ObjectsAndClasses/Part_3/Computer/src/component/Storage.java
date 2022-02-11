package component;

public class Storage {
    /*
     *Накопитель информации:
        тип — HDD, SSD,
        объём памяти,
        вес.
     */
    private final StorageType type;
    private final double volume;
    private final double weight;

    public Storage(StorageType type, double volume, double weight) {
        this.type = type;
        this.volume = volume;
        this.weight = weight;
    }

    public StorageType getType() {
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
        return "Storage{" +
                "type=" + type +
                ", volume=" + volume +
                ", weight=" + weight +
                '}';
    }
}
