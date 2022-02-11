import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        int day = 17;
        int month = 8;
        int year = 2001;

        System.out.println(collectBirthdays(year, month, day));

    }

    public static String collectBirthdays(int year, int month, int day) {

        //TODO реализуйте метод для построения строки в следующем виде
        //0 - 31.12.1990 - Mon
        //1 - 31.12.1991 - Tue

        Calendar calendar = new GregorianCalendar(year, (month - 1), day);
        Calendar nowCalendar = new GregorianCalendar();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy - E", Locale.US);

        int index = 0;
        StringBuilder stringDate = new StringBuilder();

        while (calendar.before(nowCalendar)) {
            stringDate.append(index).append(" - ").append(dateFormat.format(calendar.getTime())).append("\n");
            calendar.add(Calendar.YEAR, 1);
            index++;
        }

        return new String(stringDate);
    }
}
