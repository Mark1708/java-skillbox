import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }
            //TODO:напишите ваш код тут, результат вывести в консоль.
            //При невалидном ФИО вывести в консоль: Введенная строка не является ФИО
            Pattern pattern = Pattern.compile("^[А-ЯЁ][а-яё]*([-][А-ЯЁ][а-яё]*)?\\s[А-ЯЁ][а-яё]*\\s[А-ЯЁ][а-яё]*$");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                String[] fio = input.substring(matcher.start(), matcher.end()).split("\\s+");
                System.out.println("Фамилия: " + fio[0] + "\n" +
                        "Имя: " + fio[1] + "\n" +
                        "Отчество: " + fio[2]);
            } else {
                System.out.println("Введенная строка не является ФИО");
            }
        }
    }
}

