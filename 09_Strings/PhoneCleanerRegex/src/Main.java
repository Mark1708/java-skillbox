import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }
            //TODO:напишите ваш код тут, результат вывести в консоль.
            if (input.matches(".*((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}.*")) {
                String phoneNum = input.replaceAll("[^0-9]", "");
                if ((input.replaceAll("[^0-9]", "").length() == 11
                        && (input.replaceAll("[^0-9]", "").charAt(0) == '8'
                        || input.replaceAll("[^0-9]", "").charAt(0) == '7'))) {
                    System.out.println(phoneNum.charAt(0) == '8' ? ("7" + phoneNum.substring(1, 11)) : (phoneNum));
                } else if (input.replaceAll("[^0-9]", "").length() == 10) {
                    System.out.println("7" + phoneNum);
                } else
                    System.out.println("Неверный формат номера");
            } else
                System.out.println("Неверный формат номера");

        }
    }

}
