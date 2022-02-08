public class Main {

    public static void main(String[] args) {
        Country russia = new Country("Russia", 145478097, 17125191, "Moscow", true);
        System.out.println(russia.toString());

        Car mark2 = new Car("Toyota", "Mark 2", "white", 1800, 4, false);
        System.out.println(mark2.toString());
    }
}
