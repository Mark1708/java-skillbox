package skillbox.com.parsers;

import org.xml.sax.SAXException;
import skillbox.com.model.SAXHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SAXParser extends Parser{

    public SAXParser(String filePath) {
        super(filePath);
    }

    @Override
    public void parseFile() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            javax.xml.parsers.SAXParser parser = factory.newSAXParser();
            SAXHandler handler = new SAXHandler(birthDayFormat, visitDateFormat);
            parser.parse(super.getFile(), handler);

            super.setVoterCounts(handler.getVoterCounts());
            super.setVoterStationWorkTimes(handler.getVoterStationWorkTimes());
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

    }
}
