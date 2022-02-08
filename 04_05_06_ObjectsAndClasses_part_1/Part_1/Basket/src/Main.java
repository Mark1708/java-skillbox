public class Main {

    public static void main(String[] args) {
        Basket basket = new Basket();
        basket.add("Milk", 40, 2, 300);
        basket.add("Oil", 20, 1, 150);
        basket.print("Milk\n");

        Arithmetic arithmetic = new Arithmetic(1, 2);
        System.out.println("Сумма = " + arithmetic.getSum());
        System.out.println("Произведение = " + arithmetic.getMult());
        System.out.println("Максимальное = " + arithmetic.getMax());
        System.out.println("Минимальное = " + arithmetic.getMin() + "\n");

        Printer printer = new Printer();
        printer.append("hello");
        printer.append("name1", "tratratratratratratratratratratratratratratratratratratratratratra");
        System.out.println("PendingPageCount = " + Printer.getPendingPageCount());
        System.out.println("PrintedPageCount = " + Printer.getPrintedPageCount());
        printer.print();
        printer.append("name2", "tratratratratratratratratratratratratratratratratratratratratratra", 2);
        printer.clear();
        System.out.println("PendingPageCount = " + Printer.getPendingPageCount());
        System.out.println("PrintedPageCount = " + Printer.getPrintedPageCount());
    }
}
