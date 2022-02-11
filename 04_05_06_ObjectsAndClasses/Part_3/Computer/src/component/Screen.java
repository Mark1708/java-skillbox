package component;

public class Screen {
    /*
     *Экран:
        диагональ,
        тип (IPS, TN, VA),
        вес.
     */
    private final double diagonal;
    private final ScreenType type;
    private final double weight;

    public Screen(double diagonal, ScreenType type, double weight) {
        this.diagonal = diagonal;
        this.type = type;
        this.weight = weight;
    }

    public double getDiagonal() {
        return diagonal;
    }

    public ScreenType getType() {
        return type;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Screen{" +
                "diagonal=" + diagonal +
                ", type=" + type +
                ", weight=" + weight +
                '}';
    }
}
