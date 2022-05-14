package skillbox.com;


import org.xml.sax.SAXException;
import skillbox.com.parsers.DBConnection;
import skillbox.com.parsers.Parser;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class FastestSAXParser extends Parser {

    public FastestSAXParser(String filePath) {
        super(filePath);
    }

    @Override
    public void parseFile() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            javax.xml.parsers.SAXParser parser = factory.newSAXParser();
            FastestSAXHandler handler = new FastestSAXHandler(visitDateFormat);
            parser.parse(getFile(), handler);

            DBConnection.executeMultiInsertVoters();
            DBConnection.executeMultiInsertWorkTime();

        } catch (ParserConfigurationException | IOException | SAXException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printResults(boolean isTest) {
        try {
//            DBConnection.printVoterCounts(isTest);
            DBConnection.printWorkTimes(isTest);
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
    }
}
