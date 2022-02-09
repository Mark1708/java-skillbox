public class Dimensions {

    private final double width;
    private final double length;
    private final double height;

    public Dimensions(double width, double length, double height) {
        this.width = width;
        this.length = length;
        this.height = height;
    }

    public double getVolume() {
        return width * height * length;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Dimensions{" +
                "width=" + width +
                ", length=" + length +
                ", height=" + height +
                '}';
    }
}
