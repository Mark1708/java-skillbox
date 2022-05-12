package skillbox.com;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import skillbox.com.parsers.DBConnection;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FastestSAXHandler extends DefaultHandler {

    private StringBuilder currentValue = new StringBuilder();
    private final SimpleDateFormat visitDateFormat;

    public FastestSAXHandler(SimpleDateFormat visitDateFormat) {
        this.visitDateFormat = visitDateFormat;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try {
            if (qName.equals("voter")) {
                DBConnection.countVoter(
                        attributes.getValue("name"),
                        attributes.getValue("birthDay")
                );
            } else if (qName.equals("visit")) {
                DBConnection.addVisitTime(attributes.getValue("station"), visitDateFormat.parse(attributes.getValue("time")).getTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        currentValue.append(ch, start, length);
    }

}
