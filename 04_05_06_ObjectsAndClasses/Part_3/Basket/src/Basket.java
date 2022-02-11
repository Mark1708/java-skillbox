public class Basket {

    private static int totalBasketPrice = 0;
    private static int totalBasketCount = 0;
    private static int count = 0;
    private static double totalWeight = 0;
    private String items = "";
    private int totalPrice = 0;
    private int limit;
    private static int basketCount = 0;

    public Basket() {
        increaseCount(1);
        items = "Список товаров:";
        this.limit = 1000000;
        basketCount++;
    }

    public Basket(int limit) {
        this();
        this.limit = limit;
    }

    public Basket(String items, int totalPrice) {
        this();
        this.items = this.items + items;
        this.totalPrice = totalPrice;
    }

    public static int getCount() {
        return count;
    }

    public static void increaseCount(int count) {
        Basket.count = Basket.count + count;
    }

    public void add(String name, int price) {
        add(name, price, 1, 0);
    }

    public void add(String name, int price, int count, double weight) {
        boolean error = false;
        if (contains(name)) {
            error = true;
        }

        if (totalPrice + count * price >= limit) {
            error = true;
        }

        if (error) {
            System.out.println("Error occured :(");
            return;
        }

        items = items + "\n" + name + " - " +
            count + " шт. - " + price;
        totalPrice = totalPrice + count * price;
        increaseBasketCount(count);
        increaseBasketPrice(price, count);
        totalWeight += (count * weight);
    }

    private static double averageAllBasketPrice() {
        return totalBasketPrice / totalBasketCount;
    }

    private static double averageBasketPrice() {
        return totalBasketPrice / basketCount;
    }

    private static void increaseBasketPrice(int price, int count) {
        totalBasketPrice += totalBasketPrice + count * price;
    }

    private static void increaseBasketCount(int count) {
        totalBasketCount += count;
    }

    public void clear() {
        items = "";
        totalPrice = 0;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public static double getTotalWeight() {
        return totalWeight;
    }

    public boolean contains(String name) {
        return items.contains(name);
    }

    public void print(String title) {
        System.out.println(title);
        if (items.isEmpty()) {
            System.out.println("Корзина пуста");
        } else {
            System.out.println(items);
            System.out.println("Total weight: " + totalWeight);
            System.out.println("Total price: " + totalPrice);
        }
    }
}