package component;

public class Processor {
    /*
     *Процессор:
        частота,
        количество ядер,
        производитель,
        вес.
     */
    private final double frequency;
    private final int coresNum;
    private final String manufacturer;
    private final double weight;

    public Processor(double frequency, int coresNum, String manufacturer, double weight) {
        this.frequency = frequency;
        this.coresNum = coresNum;
        this.manufacturer = manufacturer;
        this.weight = weight;
    }

    public double getFrequency() {
        return frequency;
    }

    public int getCoresNum() {
        return coresNum;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Processor{" +
                "frequency=" + frequency +
                ", coresNum=" + coresNum +
                ", manufacturer='" + manufacturer + '\'' +
                ", weight=" + weight +
                '}';
    }
}
