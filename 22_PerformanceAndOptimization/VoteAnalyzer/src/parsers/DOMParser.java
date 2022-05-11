package parsers;

import model.Voter;
import model.WorkTime;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.Date;

public class DOMParser extends Parser{

    public DOMParser(String filePath) {
        super(filePath);
    }

    @Override
    public void parseFile() {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(super.getFile());

            findEqualVoters(doc);
            fixWorkTimes(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void findEqualVoters(Document doc) throws Exception {
        NodeList voters = doc.getElementsByTagName("voter");
        int votersCount = voters.getLength();
        for (int i = 0; i < votersCount; i++) {
            Node node = voters.item(i);
            NamedNodeMap attributes = node.getAttributes();

            String name = attributes.getNamedItem("name").getNodeValue();
            Date birthDay = birthDayFormat
                    .parse(attributes.getNamedItem("birthDay").getNodeValue());

            Voter voter = new Voter(name, birthDay);
            Byte count = super.getVoterCounts().get(voter);
            super.getVoterCounts().put(voter,count == null ? 1 : (byte) (count.intValue() + 1));
        }
    }

    private void fixWorkTimes(Document doc) throws Exception {
        NodeList visits = doc.getElementsByTagName("visit");
        int visitCount = visits.getLength();
        for (int i = 0; i < visitCount; i++) {
            Node node = visits.item(i);
            NamedNodeMap attributes = node.getAttributes();

            Integer station = Integer.parseInt(attributes.getNamedItem("station").getNodeValue());
            Date time = visitDateFormat.parse(attributes.getNamedItem("time").getNodeValue());
            WorkTime workTime = super.getVoterStationWorkTimes().get(station);
            if (workTime == null) {
                workTime = new WorkTime();
                super.getVoterStationWorkTimes().put(station, workTime);
            }
            workTime.addVisitTime(time.getTime());
        }
    }
}
