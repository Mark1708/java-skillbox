public class Main {

    public static void main(String[] args) {
        Dimensions dimensions = new Dimensions(12.2, 5.4, 8.0);

        Cargo cargo = new Cargo(dimensions, 45, "Spb", true, "1d2sdw5458w", false);

        System.out.println(cargo);

    }
}
