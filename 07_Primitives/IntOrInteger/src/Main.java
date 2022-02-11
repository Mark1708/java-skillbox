public class Main {
    public static void main(String[] args) {
        Container container = new Container();
        container.addCount(5672);
        System.out.println(container.getCount());

        // TODO: ниже напишите код для выполнения задания:
        //  С помощью цикла и преобразования чисел в символы найдите все коды
        //  букв русского алфавита — заглавных и строчных, в том числе буквы Ё.

        for (char c = 'А'; c <= 'Е'; c++) {
            System.out.println( (int) c + " - " + c);
        }
        System.out.println( (int) 'Ё' + " - " + 'Ё');
        for (char c = 'Ж'; c <= 'е'; c++) {
            System.out.println( (int) c + " - " + c);
        }
        System.out.println( (int) 'ё' + " - " + 'ё');
        for (char c = 'ж'; c <= 'я'; c++) {
            System.out.println( (int) c + " - " + c);
        }

    }
}
