public class Car {
    private String brand;
    private String model;
    private String color;
    private double weight;
    private int doorsCount;
    private boolean hasAutomaticTransmission;

    public Car(String brand, String model, String color, double weight, int doorsCount, boolean hasAutomaticTransmission) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.weight = weight;
        this.doorsCount = doorsCount;
        this.hasAutomaticTransmission = hasAutomaticTransmission;
    }

    public Car() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getDoorsCount() {
        return doorsCount;
    }

    public void setDoorsCount(int doorsCount) {
        this.doorsCount = doorsCount;
    }

    public boolean isHasAutomaticTransmission() {
        return hasAutomaticTransmission;
    }

    public void setHasAutomaticTransmission(boolean hasAutomaticTransmission) {
        this.hasAutomaticTransmission = hasAutomaticTransmission;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", weight=" + weight +
                ", doorsCount=" + doorsCount +
                ", hasAutomaticTransmission=" + hasAutomaticTransmission +
                '}';
    }
}
