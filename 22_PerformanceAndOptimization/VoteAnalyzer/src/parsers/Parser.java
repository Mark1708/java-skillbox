package parsers;

import model.Voter;
import model.WorkTime;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class Parser {

    public static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    public static SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    private HashMap<Integer, WorkTime> voterStationWorkTimes;
    private HashMap<Voter, Byte> voterCounts;
    private final File file;
    private final String filePath;

    public Parser(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
        this.voterStationWorkTimes = new HashMap<>();
        this.voterCounts = new HashMap<>();
    }

    public HashMap<Integer, WorkTime> getVoterStationWorkTimes() {
        return voterStationWorkTimes;
    }

    public HashMap<Voter, Byte> getVoterCounts() {
        return voterCounts;
    }

    public void setVoterStationWorkTimes(HashMap<Integer, WorkTime> voterStationWorkTimes) {
        this.voterStationWorkTimes = voterStationWorkTimes;
    }

    public void setVoterCounts(HashMap<Voter, Byte> voterCounts) {
        this.voterCounts = voterCounts;
    }

    public String getFilePath() {
        return filePath;
    }

    public File getFile() {
        return file;
    }

    public void printResults() {
        StringBuilder sb = new StringBuilder();
        sb.append("Voting station work times: \n");
        for (Integer votingStation : getVoterStationWorkTimes().keySet()) {
            WorkTime workTime = getVoterStationWorkTimes().get(votingStation);
            sb.append("\t").append(votingStation).append(" - ").append(workTime).append("\n");
        }
        System.out.print(sb);
        sb.delete(0, sb.length());

        sb.append("Duplicated voters: \n");
        for (Voter voter : getVoterCounts().keySet()) {
            Integer count = getVoterCounts().get(voter).intValue();
            if (count > 1) {
                sb.append("\t").append(voter).append(" - ").append(count).append("\n");
            }
        }
        System.out.print(sb);
        sb.delete(0, sb.length());
    }



    public void parseFile() {
    }


}
