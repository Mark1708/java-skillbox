import java.time.LocalDate;
import java.time.Period;

public class Main {

    public static void main(String[] args) {
        System.out.println(getPeriodFromBirthday(LocalDate.of(2015, 5, 12), LocalDate.now()));
    }

    public static String getPeriodFromBirthday(LocalDate firstDate, LocalDate secondDate) {
        Period period = Period.between(firstDate, secondDate);
        return String.format("years: %d, months: %d, days: %d", period.getYears(), period.getMonths(), period.getDays());
    }
}
