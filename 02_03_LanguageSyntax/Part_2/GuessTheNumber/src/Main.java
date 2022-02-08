import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int value = new Random().nextInt(100);

        while (true) {
            System.out.print("Введите число: ");
            int attempt = new Scanner(System.in).nextInt();

            if (value > attempt)
                System.out.println("Заданное число больше");
            else if (value < attempt)
                System.out.println("Заданное число меньше");
            else {
                System.out.println("Вы угадали!");
                break;
            }
        }

    }
}
