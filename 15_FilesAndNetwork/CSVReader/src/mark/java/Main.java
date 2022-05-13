package mark.java;

import java.io.*;
import java.util.ArrayList;

public class Main {

    private static ArrayList<Operation> readCSV(String path, String csvSplitBy){
        BufferedReader br = null;
        String line = "";
        ArrayList<Operation> operationList = new ArrayList<>();
        int i = 0;
        try {
            br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                if (i != 0)
                    operationList.add(new Operation(line.split(csvSplitBy)));
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return operationList;
    }
    public static void main(String[] args) {
        String csvFile = "src/mark/resources/movementList.csv";
        String cvsSplitBy = ",";

        ArrayList<Operation> operationList = readCSV(csvFile, cvsSplitBy);

        System.out.println("Сумма расходов: " + operationList.stream().mapToDouble(Operation::getExpensesAmount).sum() + " руб.\n" +
                "Сумма доходов: " + operationList.stream().mapToDouble(Operation::getIncomingAmount).sum() + " руб.\n" +
                " \n" +
                "Суммы расходов по организациям:");
        operationList.forEach(operation ->
                System.out.println(
                        operation.getDescription() + "\t" + operation.getExpensesAmount() + " руб."
                )
        );

    }
}
