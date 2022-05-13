

public class Main {

    public static void main(String[] args) {
        // Парсим данные о метро в файл
        Parser.parseInFile();


        // Читаем полученный файл и получаем данные о метро
        Parser.createStationIndex();
        StationIndex stationIndex = Parser.getStationIndex();

        System.out.println("Line info \n-----------------");
        stationIndex.number2line.forEach(
                (num, line) -> {
                    System.out.println(line.getNumber() + "\t" + line.getName() + "\t" + line.getStations().size() + " stations");
                }
        );

        System.out.println("\nConnection info \n-----------------");
        System.out.println("Connection quantity - " + stationIndex.connections.size());

        System.out.println("Successes");

    }

}
