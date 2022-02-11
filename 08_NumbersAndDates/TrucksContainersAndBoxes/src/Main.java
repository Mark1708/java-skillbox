import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String boxes = scanner.nextLine();

        int boxCounter, containerCounter = 1, cargoCarCounter = 1;

        boxCounter = Integer.parseInt(boxes);


        for (int i = 1; i <= boxCounter; i++) {
            if (i % 27 == 1 && containerCounter % 12 == 1) {
                System.out.println("Грузовик: " + cargoCarCounter);
                cargoCarCounter ++;
            }
            if (i % 27 == 1){
                System.out.println("\tКонтейнер: " + containerCounter);
                containerCounter++;
            }
            System.out.println("\t\tЯщик: " + i);
        }
        System.out.println("Необходимо:\n" +
                "грузовиков - " + --cargoCarCounter + " шт.\n" +
                "контейнеров - " + --containerCounter + " шт.");

    }
}
