package skillbox.com.model;

import java.util.Date;
import java.util.TreeSet;

public class WorkTime {

    private TreeSet<TimePeriod> periods;

    /**
     * Set of model.TimePeriod objects
     */
    public WorkTime() {
        periods = new TreeSet<>();
    }

    public void addVisitTime(long visitTime) {
        Date visit = new Date(visitTime);
        TimePeriod newPeriod = new TimePeriod(visit, visit);
        for (TimePeriod period : periods) {
            if (period.compareTo(newPeriod) == 0) {
                period.appendTime(visit);
                return;
            }
        }
        periods.add(new TimePeriod(visit, visit));
    }

    public String toString() {
        StringBuilder line = new StringBuilder();
        for (TimePeriod period : periods) {
            if (!line.toString().isEmpty()) {
                line.append(", ");
            }
            line.append(period);
        }
        return line.toString();
    }
}
