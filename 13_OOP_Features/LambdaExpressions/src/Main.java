import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;


import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import static com.skillbox.airport.Airport.getInstance;

public class  Main {
    private static String staffFile = "data/staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args) {
        ArrayList<Employee> staff = loadStaffFromFile();

        staff.stream()
                .filter(
                        employee -> "2017".equals(
                                new SimpleDateFormat("yyyy").format(employee.getWorkStart())
                        )
                )
                .max(
                        Comparator.comparing(Employee::getSalary)
                )
                .ifPresent(
                        employee -> System.out.println(employee.getName())
                );

        List<Terminal> terminals = getInstance().getTerminals();

        Date dat0 = new Date();

        Date dat = new Date();

        dat.setTime(dat.getTime() + 1000 * 60 * 60 * 2);

        terminals.forEach(
                terminal ->
                        terminal.getFlights().forEach(
                                flight -> {
                                    if (flight.getType().equals(Flight.Type.DEPARTURE) && flight.getDate().before(dat) && flight.getDate().after(dat0))
                                        System.out.println(
                                                new SimpleDateFormat("k:m")
                                                        .format(flight.getDate()) + " " + flight.getAircraft().getModel()
                                        );

                                }
                        )
        );

    }

    private static ArrayList<Employee> loadStaffFromFile() {
        ArrayList<Employee> staff = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for(String line : lines) {
                String[] fragments = line.split("\t");
                if(fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                staff.add(new Employee(
                    fragments[0],
                    Integer.parseInt(fragments[1]),
                    (new SimpleDateFormat(dateFormat)).parse(fragments[2])
                ));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return staff;
    }
}