import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RouteCalculatorTest extends TestCase {

    private StationIndex stationIndex = new StationIndex();
    private RouteCalculator routeCalculator = new RouteCalculator(stationIndex);;

    @Override
    public void setUp() throws Exception {

        stationIndex.addLine(new Line(1, "Русская"));
        stationIndex.addLine(new Line(2, "Английская"));
        stationIndex.addLine(new Line(3, "Немецкая"));

        stationIndex.getLine(1).addStation(new Station("Первая", stationIndex.getLine(1)));
        stationIndex.getLine(1).addStation(new Station("Вторая", stationIndex.getLine(1)));
        stationIndex.getLine(1).addStation(new Station("Третья", stationIndex.getLine(1)));
        stationIndex.getLine(1).addStation(new Station("Четвертая", stationIndex.getLine(1)));
        stationIndex.getLine(1).addStation(new Station("Пятая", stationIndex.getLine(1)));

        stationIndex.addStation(new Station("Первая", stationIndex.getLine(1)));
        stationIndex.addStation(new Station("Вторая", stationIndex.getLine(1)));
        stationIndex.addStation(new Station("Третья", stationIndex.getLine(1)));
        stationIndex.addStation(new Station("Четвертая", stationIndex.getLine(1)));
        stationIndex.addStation(new Station("Пятая", stationIndex.getLine(1)));

        stationIndex.getLine(2).addStation(new Station("First", stationIndex.getLine(2)));
        stationIndex.getLine(2).addStation(new Station("Second", stationIndex.getLine(2)));
        stationIndex.getLine(2).addStation(new Station("Third", stationIndex.getLine(2)));
        stationIndex.getLine(2).addStation(new Station("Fourth", stationIndex.getLine(2)));
        stationIndex.getLine(2).addStation(new Station("Fifth", stationIndex.getLine(2)));

        stationIndex.addStation(new Station("First", stationIndex.getLine(2)));
        stationIndex.addStation(new Station("Second", stationIndex.getLine(2)));
        stationIndex.addStation(new Station("Third", stationIndex.getLine(2)));
        stationIndex.addStation(new Station("Fourth", stationIndex.getLine(2)));
        stationIndex.addStation(new Station("Fifth", stationIndex.getLine(2)));

        stationIndex.getLine(3).addStation(new Station("Erster", stationIndex.getLine(3)));
        stationIndex.getLine(3).addStation(new Station("Zweiter", stationIndex.getLine(3)));
        stationIndex.getLine(3).addStation(new Station("Dritter", stationIndex.getLine(3)));
        stationIndex.getLine(3).addStation(new Station("Vierter", stationIndex.getLine(3)));
        stationIndex.getLine(3).addStation(new Station("Funfter", stationIndex.getLine(3)));

        stationIndex.addStation(new Station("Erster", stationIndex.getLine(3)));
        stationIndex.addStation(new Station("Zweiter", stationIndex.getLine(3)));
        stationIndex.addStation(new Station("Dritter", stationIndex.getLine(3)));
        stationIndex.addStation(new Station("Vierter", stationIndex.getLine(3)));
        stationIndex.addStation(new Station("Funfter", stationIndex.getLine(3)));

        stationIndex.addConnection(
                new ArrayList<Station>(
                        Arrays.asList(
                                stationIndex.getStation("Вторая"),
                                stationIndex.getStation("Third"))
                )
        );

        stationIndex.addConnection(
                new ArrayList<Station>(
                        Arrays.asList(
                                stationIndex.getStation("Fourth"),
                                stationIndex.getStation("Dritter"))
                )
        );
    }

    public void testGetShortestRoute(){
        Station from = stationIndex.getStation("Первая");
        Station to = stationIndex.getStation("Funfter");
        List<Station> actual =  routeCalculator.getShortestRoute(from, to);
        List<Station> expected = new ArrayList<>(
                Arrays.asList(
                        stationIndex.getStation("Первая"),
                        stationIndex.getStation("Вторая"),
                        stationIndex.getStation("Third"),
                        stationIndex.getStation("Fourth"),
                        stationIndex.getStation("Dritter"),
                        stationIndex.getStation("Vierter"),
                        stationIndex.getStation("Funfter")
                        )
        );
        assertEquals(expected, actual);
    }

    public void testCalculateDuration(){
        Station from = stationIndex.getStation("Первая");
        Station to = stationIndex.getStation("Funfter");
        List<Station> route =  routeCalculator.getShortestRoute(from, to);
        double actual = routeCalculator.calculateDuration(route);
        double expected = 17;

        assertEquals(expected, actual);
    }
    
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
