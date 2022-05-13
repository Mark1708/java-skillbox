import exceptions.MyException;

import java.util.Scanner;

public class Main {
    private static String addCommand = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
    private static String commandExamples = "\t" + addCommand + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров";
    private static String commandError = "Wrong command! Available command examples: \n" +
            commandExamples;
    private static String helpText = "Command examples:\n" + commandExamples;

    public static void main(String[] args) throws MyException {
        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();
        for (;;) {
            String command = scanner.nextLine();
            try {
                String[] tokens = command.split("\\s+", 2);
                if ("add".equals(tokens[0])) {
                    executor.addCustomer(tokens[1]);
                } else if ("list".equals(tokens[0])) {
                    executor.listCustomers();
                } else if ("remove".equals(tokens[0])) {
                    executor.removeCustomer(tokens[1]);
                } else if ("count".equals(tokens[0])) {
                    System.out.println("There are " + executor.getCount() + " customers");
                } else if ("help".equals(tokens[0])) {
                    System.out.println(helpText);
                }
            } catch (ArrayIndexOutOfBoundsException exArr) {
                throw new ArrayIndexOutOfBoundsException(commandError);
            }
        }
    }
}
