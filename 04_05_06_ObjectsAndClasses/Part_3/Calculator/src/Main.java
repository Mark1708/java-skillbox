public class Main {

    public static void main(String[] args) {
        ArithmeticCalculator arithmeticCalculator = new ArithmeticCalculator(5, 3);
        System.out.printf("Сложение: %f\nВычитание: %f\nПроизведение: %f",
                arithmeticCalculator.calculate(Operation.ADD),
                arithmeticCalculator.calculate(Operation.SUBTRACT),
                arithmeticCalculator.calculate(Operation.MULTIPLY));
    }
}
