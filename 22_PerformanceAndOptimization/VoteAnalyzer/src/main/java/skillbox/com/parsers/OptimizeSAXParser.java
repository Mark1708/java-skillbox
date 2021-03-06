package skillbox.com.parsers;


import org.xml.sax.SAXException;
import skillbox.com.model.OptimizeSAXHandler;
import skillbox.com.model.Voter;
import skillbox.com.model.WorkTime;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class OptimizeSAXParser extends Parser{

    public OptimizeSAXParser(String filePath) {
        super(filePath);
    }

    @Override
    public void parseFile() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            javax.xml.parsers.SAXParser parser = factory.newSAXParser();
            OptimizeSAXHandler handler = new OptimizeSAXHandler(birthDayFormat, visitDateFormat);
            parser.parse(getFile(), handler);

            setVoterCounts(handler.getVoterCounts());
            setVoterStationWorkTimes(handler.getVoterStationWorkTimes());
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printResults(boolean isTest) {
        StringBuilder sb = new StringBuilder();
        sb.append("Voting station work times: \n");
        for (Integer votingStation : getVoterStationWorkTimes().keySet()) {
            WorkTime workTime = getVoterStationWorkTimes().get(votingStation);
            sb.append("\t").append(votingStation).append(" - ").append(workTime).append("\n");
        }
        if (!isTest) System.out.print(sb);
        sb.delete(0, sb.length());

        sb.append("Duplicated voters: \n");
        for (Voter voter : getVoterCounts().keySet()) {
            Integer count = getVoterCounts().get(voter).intValue();
            if (count > 1) {
                sb.append("\t").append(voter).append(" - ").append(count).append("\n");
            }
        }
        if (!isTest) System.out.print(sb);
        sb.delete(0, sb.length());
    }
}
