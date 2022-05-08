import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class Loader {

    /*
    Регионы решил спарсить для правдоподобности, так как у каждого региона есть несколько вариаций кода
    Результат работы - папка numbers с txt файла для каждого региона
    Статистика:
        1) Duration: 14387 ms
        2) Duration: 14026 ms
        3) Duration: 13596 ms

        Count of regions: 86
        Count of numbers: 220962816
    */

    private static List<Thread> threads = new ArrayList<>();

    public static void main(String[] args) throws Exception {
//        parseRealRegionsCode();

        HashMap<String, List<Integer>> regs = getRegionsCode();

        try {
            int processorsCount = Runtime.getRuntime().availableProcessors();
            int regsPerThread = regs.size() / processorsCount;
            int oneMoreSizeCount = regs.size() % processorsCount;
            int bound = oneMoreSizeCount * (regsPerThread + 1);

            HashMap<String, List<Integer>> subMapOneMore = subMap(regs,0, bound);
            HashMap<String, List<Integer>> subMapStandard = subMap(regs, bound, regs.size());

            for (int i = 0; i < processorsCount; i++) {
                int lowBound = (i < oneMoreSizeCount) ? i * (regsPerThread + 1) : (i - oneMoreSizeCount) * regsPerThread;
                int upBound = (i < oneMoreSizeCount) ? lowBound + regsPerThread + 1 : lowBound + regsPerThread;

                HashMap<String, List<Integer>> perThread = (i < oneMoreSizeCount) ? subMap(subMapOneMore, lowBound, upBound) : subMap(subMapStandard, lowBound, upBound);
                NumGenerator numGenerator = new NumGenerator(perThread);

                Thread thread = new Thread(numGenerator);
                thread.start();
                threads.add(thread);
            }
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

        System.out.println("Duration: " + NumGenerator.duration + " ms");
        System.out.println("Count of regions: " + regs.size());
        System.out.println("Count of numbers: " + NumGenerator.counter.get());
    }

    private static HashMap<String, List<Integer>> subMap(HashMap<String, List<Integer>> map, int startBound, int endBound) {
        List<String> keyList = new ArrayList<>(map.keySet()).subList(startBound, endBound);
        HashMap<String, List<Integer>> newMap = new HashMap<>();
        for (String key: keyList)
            if (map.containsKey(key))
                newMap.put(key, map.get(key));
        return newMap;
    }

    private static HashMap<String, List<Integer>> getRegionsCode() throws IOException {
        HashMap<String, List<Integer>> regs = new HashMap<String, List<Integer>>(){{put("199", new ArrayList<Integer>(){{add(199);}});}};
        try (JsonReader reader = new JsonReader(new FileReader("src/main/resources/regions.json"))) {
            Gson gson = new Gson();
            Type gsonType = new TypeToken<HashMap<String, List<Integer>>>(){}.getType();
            regs = gson.fromJson(reader, gsonType);
        }
        return regs;
    }

    private static void parseRealRegionsCode() throws IOException {
        Document doc = Jsoup.connect("http://kcbux.ru/Spravochnik/sp_05_subekt.html").get();
        Elements regions = doc.select("#tab1 > tbody > tr");
        HashMap<String, List<Integer>> regs = new HashMap<>();
        for (int i = 1; i < regions.size(); i++) {
            Elements row = regions.get(i).select("td");
            List<Integer> regionCodes = Arrays.stream(row.get(2).text().split(", ")).map(Integer::parseInt).collect(Collectors.toList());
            regs.put(row.get(0).text(), regionCodes);
        }
        try (Writer writer = new FileWriter("src/main/resources/regions.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(regs, writer);
        }
    }
}
