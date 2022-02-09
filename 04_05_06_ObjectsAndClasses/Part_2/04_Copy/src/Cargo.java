import java.awt.*;

public class Cargo {
    private Dimensions dimensions;
    private double weight;
    private String address;
    private final boolean isTurnOver;
    private final String registrationNumber;
    private final boolean isFragile;

    public Cargo(Dimensions dimensions, double weight, String address, boolean isTurnOver, String registrationNumber, boolean isFragile) {
        this.dimensions = dimensions;
        this.weight = weight;
        this.address = address;
        this.isTurnOver = isTurnOver;
        this.registrationNumber = registrationNumber;
        this.isFragile = isFragile;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isTurnOver() {
        return isTurnOver;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public boolean isFragile() {
        return isFragile;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "dimensions=" + dimensions +
                ", dimensions_volume=" + dimensions.getVolume() +
                ", weight=" + weight +
                ", address='" + address + '\'' +
                ", isTurnOver=" + isTurnOver +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", isFragile=" + isFragile +
                '}';
    }
}
