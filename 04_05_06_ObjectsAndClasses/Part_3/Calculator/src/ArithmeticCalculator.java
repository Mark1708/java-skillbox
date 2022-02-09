public class ArithmeticCalculator {
    private double a;
    private double b;

    public ArithmeticCalculator(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double calculate(Operation operation) {
        switch (operation) {
            case ADD:
                return  a + b;
            case MULTIPLY:
                return a * b;
            case SUBTRACT:
                return a - b;
        }
        return -1;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }
}
