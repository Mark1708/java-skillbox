package skillbox.com.model;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class OptimizeSAXHandler extends DefaultHandler {

    private StringBuilder currentValue = new StringBuilder();
    private Voter voter;
    private final SimpleDateFormat birthDayFormat;
    private final SimpleDateFormat visitDateFormat;
    private HashMap<Voter, Byte> voterCounts;
    private HashMap<Integer, WorkTime> voterStationWorkTimes;

    public OptimizeSAXHandler(SimpleDateFormat birthDayFormat, SimpleDateFormat visitDateFormat) {
        this.birthDayFormat = birthDayFormat;
        this.visitDateFormat = visitDateFormat;
        this.voterCounts = new HashMap<>();
        this.voterStationWorkTimes = new HashMap<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)  {
        try {
            if (qName.equals("voter") && voter == null) {
                Date birthdate = birthDayFormat.parse(attributes.getValue("birthDay"));
                voter = new Voter(attributes.getValue("name"), birthdate);
            } else if (qName.equals("visit") && voter != null) {
                byte count = voterCounts.getOrDefault(voter, (byte) 0);
                voterCounts.put(voter, (byte) (count + 1));

                Integer station = Integer.parseInt(attributes.getValue("station"));
                WorkTime workTime = voterStationWorkTimes.get(station);
                if (workTime == null) {
                    workTime = new WorkTime();
                    voterStationWorkTimes.put(station, workTime);
                }
                workTime.addVisitTime(visitDateFormat.parse(attributes.getValue("time")).getTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("voter")) {
            voter = null;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        currentValue.append(ch, start, length);
    }

    public HashMap<Voter, Byte> getVoterCounts() {
        return voterCounts;
    }

    public HashMap<Integer, WorkTime> getVoterStationWorkTimes() {
        return voterStationWorkTimes;
    }

}
