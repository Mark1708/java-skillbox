import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import core.Connection;
import core.Line;
import core.Station;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Parser {

    private static String dataFile = "src/main/resources/map.json";

    /* Parse in url */

    public static void parseInFile() {
        String url = "https://www.moscowmap.ru/metro.html#lines";

        Elements parsedElements = Parser.parseUrl(url);

        HashMap<String, Line> metroMap = Parser.getMapMetro(parsedElements);

        HashSet<Connection> connectionSet = Parser.getStationsConnections(parsedElements, metroMap);

        Parser.writeInFile(metroMap, connectionSet);
    }

    public static Elements parseUrl(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).maxBodySize(0).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc.getElementsByAttribute("data-line");
    }

    public static HashMap<String, Line> getMapMetro(Elements map) {
        HashMap<String, Line> MetroMap = new HashMap<>();
        // Заполняем линии
        map.forEach(element -> {
            if (element.hasAttr("data-line") && element.tagName().equals("span")) {
                MetroMap.put(
                        element.attr("data-line"),
                        new Line(element.attr("data-line"), element.text())
                );
            } else if (element.hasAttr("data-line") && element.tagName().equals("div")) {
                element.getElementsByTag("p").forEach(p -> {
                    String numLineFirstStation = element.attr("data-line");
                    Elements spanElements = p.getElementsByTag("span");

                    // Добавляем станцию
                    MetroMap.get(numLineFirstStation)
                            .addStation(
                                    new Station(
                                            spanElements.get(1).text(),
                                            MetroMap.get(numLineFirstStation)
                                    )
                            );
                });
            }
        });
        return MetroMap;
    }

    public static HashSet<Connection> getStationsConnections(Elements map, HashMap<String, Line> MetroMap) {
        HashSet<Connection> connectionsList = new HashSet<>();

        // Получаем данные о станциях и переходах
        map.forEach(element -> {
            if (element.hasAttr("data-line") && element.tagName().equals("div")) {
                element.getElementsByTag("p").forEach(p -> {
                    String numLineFirstStation = element.attr("data-line");
                    Elements spanElements = p.getElementsByTag("span");
                    String nameStation = spanElements.get(1).text();

                    // Если есть переходы добавляем
                    if (spanElements.size() >= 3) {
                        Station stationOnMap = getStationByName(MetroMap.get(numLineFirstStation).getStations(), nameStation);
                        if (!stationOnMap.hasConnection()) {
                            Connection newConnection = new Connection();
                            newConnection.addStation(
                                    getStationByName(MetroMap.get(numLineFirstStation).getStations(), nameStation)

                            );
                            getStationByName(MetroMap.get(numLineFirstStation).getStations(), nameStation).setHasConnection(true);
                            for (int i = 2; i < spanElements.size(); i++) {
                                Element connectSation = spanElements.get(i);
                                String stationName = connectSation.attr("title").substring(
                                        connectSation.attr("title").indexOf("«") + 1,
                                        connectSation.attr("title").lastIndexOf("»")
                                );
                                String lineNumber = connectSation.attr("class").substring(
                                        connectSation.attr("class").lastIndexOf("-") + 1
                                );
                                newConnection.addStation(
                                        getStationByName(MetroMap.get(lineNumber).getStations(), stationName)

                                );
                                getStationByName(MetroMap.get(lineNumber).getStations(), stationName).setHasConnection(true);
                            }
                            connectionsList.add(newConnection);
                        }

                    }


                });
            }
        });
        return connectionsList;
    }

    public static void writeInFile(HashMap<String, Line> metroMap, HashSet<Connection> connectionSet) {
        JSONObject resultJson = new JSONObject();
        JSONObject stations = new JSONObject();
        JSONArray connectionList = new JSONArray();
        JSONArray lines = new JSONArray();

        try {
            FileWriter file = new FileWriter(dataFile);
            ObjectMapper mapper = new ObjectMapper();

            metroMap.forEach(
                    (num, lineObject) -> {
                        JSONArray stationLine = new JSONArray();
                        lineObject.getStations().forEach(
                                stationObject -> {
                                    stationLine.add(stationObject.getName());
                                }
                        );
                        stations.put(lineObject.getNumber(), stationLine);
                        JSONObject line = new JSONObject();
                        line.put("number", lineObject.getNumber());
                        line.put("name", lineObject.getName());
                        lines.add(line);
                    }
            );


            connectionSet.forEach(connectionObject -> {
                JSONArray connection = new JSONArray();
                connectionObject.getConnections().forEach(
                        stationObject -> {
                            JSONObject station = new JSONObject();
                            station.put("line", stationObject.getLine().getNumber());
                            station.put("station", stationObject.getName());
                            connection.add(station);
                        }
                );
                connectionList.add(connection);
            });


            resultJson.put("stations", stations);
            resultJson.put("connections", connectionList);
            resultJson.put("lines", lines);

            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            file.write(mapper.writeValueAsString(resultJson));
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Station getStationByName(List<Station> stations, String name) {
        return stations.stream().filter(station -> station.getName().equals(name)).findFirst().get();
    }


    /* Parse in file */
    private static StationIndex stationIndex;

    public static StationIndex getStationIndex() {
        return stationIndex;
    }

    public static void createStationIndex() {
        stationIndex = new StationIndex();
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(getJsonFile());

            JSONArray linesArray = (JSONArray) jsonData.get("lines");
            parseLines(linesArray);

            JSONObject stationsObject = (JSONObject) jsonData.get("stations");
            parseStations(stationsObject);

            JSONArray connectionsArray = (JSONArray) jsonData.get("connections");
            parseConnections(connectionsArray);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void parseConnections(JSONArray connectionsArray) {
        connectionsArray.forEach(connectionObject ->
        {
            JSONArray connection = (JSONArray) connectionObject;
            List<Station> connectionStations = new ArrayList<>();
            connection.forEach(item ->
            {
                JSONObject itemObject = (JSONObject) item;
                String lineNumber = ((String) itemObject.get("line"));
                String stationName = (String) itemObject.get("station");

                Station station = stationIndex.getStation(stationName, lineNumber);
                station.setHasConnection(true);
                if (station == null) {
                    throw new IllegalArgumentException("core.Station " +
                            stationName + " on line " + lineNumber + " not found");
                }
                connectionStations.add(station);
            });
            stationIndex.addConnection(connectionStations);
        });
    }

    private static void parseStations(JSONObject stationsObject) {
        stationsObject.keySet().forEach(lineNumberObject ->
        {
            String lineNumber = (String) lineNumberObject;
            Line line = stationIndex.getLine(lineNumber);
            JSONArray stationsArray = (JSONArray) stationsObject.get(lineNumberObject);
            stationsArray.forEach(stationObject ->
            {
                Station station = new Station((String) stationObject, line);
                stationIndex.addStation(station);
                line.addStation(station);
            });
        });
    }

    private static void parseLines(JSONArray linesArray) {
        linesArray.forEach(lineObject -> {
            JSONObject lineJsonObject = (JSONObject) lineObject;
            Line line = new Line(
                    ((String) lineJsonObject.get("number")),
                    (String) lineJsonObject.get("name")
            );
            stationIndex.addLine(line);
        });
    }

    private static String getJsonFile() {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(dataFile));
            lines.forEach(line -> builder.append(line));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }
}

