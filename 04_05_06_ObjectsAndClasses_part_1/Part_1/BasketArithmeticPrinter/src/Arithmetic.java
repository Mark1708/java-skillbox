public class Arithmetic {
    private int a;
    private int b;

    public Arithmetic(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getSum() {
        return a + b;
    }

    public int getMult() {
        return a * b;
    }

    public int getMax() {
        return a > b ? a : b;
    }

    public int getMin() {
        return a < b ? a : b;
    }
}
