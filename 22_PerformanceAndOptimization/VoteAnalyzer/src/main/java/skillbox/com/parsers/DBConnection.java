package skillbox.com.parsers;

import skillbox.com.model.WorkTime;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class DBConnection {

    private static Connection connection;

    private static String dbName = "task";
    private static String dbUser = "root";
    private static String dbPass = "password";

    private static StringBuilder insertQueryCounts = new StringBuilder();
    private static StringBuilder insertQueryWorkTime = new StringBuilder();

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/" + dbName +
                                "?user=" + dbUser + "&password=" + dbPass + "&characterEncoding=utf8");
                connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
                connection.createStatement().execute("CREATE TABLE voter_count(" +
                        "id INT NOT NULL AUTO_INCREMENT, " +
                        "name TINYTEXT NOT NULL, " +
                        "birthDate DATE NOT NULL, " +
                        "`count` INT NOT NULL, " +
                        "PRIMARY KEY(id), " +
                        "KEY(name(50)), " +
                        "UNIQUE KEY name_date(name(50), birthDate))");
                connection.createStatement().execute("DROP TABLE IF EXISTS work_time");
                connection.createStatement().execute("CREATE TABLE work_time(" +
                        "id INT NOT NULL AUTO_INCREMENT, " +
                        "station INT NOT NULL, " +
                        "visitTime DATETIME NOT NULL, " +
                        "PRIMARY KEY(id), " +
                        "KEY(station))");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void countVoter(String name, String birthDay) throws SQLException {
        birthDay = birthDay.replace('.', '-');

        insertQueryCounts.append((insertQueryCounts.length() == 0 ? "" : ", "));
        insertQueryCounts.append("('" + name + "', '" + birthDay + "', 1)");
        if (insertQueryCounts.toString().length() > 1_000_000){
            executeMultiInsertVoters();
            insertQueryCounts = new StringBuilder();
        }
    }

    public static void executeMultiInsertVoters() throws SQLException {
        try {
            String sql = "INSERT INTO voter_count(name, birthDate, `count`) " +
                    "VALUES" + insertQueryCounts.toString() +
                    "ON DUPLICATE KEY UPDATE `count` = `count` + 1";
            DBConnection.getConnection().createStatement().execute(sql);

        } catch (java.lang.OutOfMemoryError e) {
            System.out.println(insertQueryCounts.toString().length());
        }
    }

    public static void printVoterCounts(boolean isTest) throws SQLException {
        String sql = "SELECT name, birthDate, `count` FROM voter_count WHERE `count` > 1";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        StringBuilder sb = new StringBuilder("\nVoting station work times: \n");
        while (rs.next()) {
            sb.append("\t").append(rs.getString("name"))
                    .append(" (").append(rs.getString("birthDate"))
                    .append(") - ").append(rs.getInt("count") + "\n");
            if (sb.toString().length() > 1024) {
                if (!isTest) System.out.print(sb);
                sb = new StringBuilder();
            }
        }
        if (!isTest) System.out.print(sb);
        rs.close();
    }

    public static void executeMultiInsertWorkTime() throws SQLException {
        String sql = "INSERT INTO work_time(station, visitTime) " +
                "VALUES" + insertQueryWorkTime.toString();
        DBConnection.getConnection().createStatement().execute(sql);
    }

    public static void addVisitTime(String station, long time) throws SQLException {
        Timestamp visitTime = new Timestamp(time);

        insertQueryWorkTime.append((insertQueryWorkTime.length() == 0 ? "" : ", "));
        insertQueryWorkTime.append("(" + station + ", '" + visitTime + "')");
        if (insertQueryWorkTime.toString().length() > 1_000_000){
            executeMultiInsertWorkTime();
            insertQueryWorkTime = new StringBuilder();
        }
    }

//    public static void printWorkTimes() throws SQLException {
//        String sql = "SELECT station, GROUP_CONCAT(visitTime) AS date_list FROM work_time GROUP BY station";
//        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
//        StringBuilder sb = new StringBuilder("\n\nDuplicated voters: \n");
//        while (rs.next()) {
//            int station = rs.getInt("station");
//            WorkTime workTime = new WorkTime();
//            System.out.println(rs.getString("date_list").split(",").length + " " +
//                     rs.getString("date_list").length());
//            Arrays.asList(rs.getString("date_list").split(","))
//                    .forEach(stringDate -> {
//                        Date date = null;
//                        try {
//                            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(stringDate);
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                        }
//                        workTime.addVisitTime(date.getTime());
//                    });
//            sb.append("\t").append(station).append(" - ").append(workTime).append("\n");
//            if (sb.toString().length() > 1024) {
////                System.out.print(sb);
//                sb = new StringBuilder();
//            }
//        }
////        System.out.print(sb);
//        rs.close();
//    }

    public static void printWorkTimes(boolean isTest) throws SQLException, ParseException {
        String sql = "SELECT station, visitTime FROM work_time";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        HashMap<Integer, WorkTime> voterStationWorkTimes = new HashMap<>();
        StringBuilder sb = new StringBuilder("\n\nDuplicated voters: \n");
        while (rs.next()) {
            int station = rs.getInt("station");
            Date visitTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("visitTime"));
            WorkTime workTime = voterStationWorkTimes.get(station);
            if (workTime == null) {
                workTime = new WorkTime();
                voterStationWorkTimes.put(station, workTime);
            }
            workTime.addVisitTime(visitTime.getTime());
        }
        for (Integer station: voterStationWorkTimes.keySet()) {
            sb.append("\t").append(station).append(" - ").append(voterStationWorkTimes.get(station)).append("\n");
            if (sb.toString().length() > 1024) {
                if (!isTest) System.out.print(sb);
                sb = new StringBuilder();
            }
        }
        if (!isTest) System.out.print(sb);
        rs.close();
    }
}
