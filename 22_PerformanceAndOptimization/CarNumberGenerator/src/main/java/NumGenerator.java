import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class NumGenerator implements Runnable {

    public static volatile long duration;
    public static AtomicInteger counter = new AtomicInteger(0);
    private final HashMap<String, List<Integer>> regs;
    private final char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

    public NumGenerator(HashMap<String, List<Integer>> regs) {
        this.regs = regs;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        try {
            regs.forEach(this::generateNum);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        duration = System.currentTimeMillis() - start;
    }

    private void generateNum(String code, List<Integer> reg) {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(String.format("src/main/resources/numbers/%s.txt", code)), StandardCharsets.UTF_8
                )
        )) {
            for (int number = 1; number < 1000; number++) {
                StringBuilder builder = new StringBuilder();
                for (int regionCode : reg) {
                    for (char firstLetter : letters) {
                        for (char secondLetter : letters) {
                            for (char thirdLetter : letters) {
                                counter.incrementAndGet();
                                builder.append(firstLetter);
                                builder.append(padNumber(number, 3));
                                builder.append(secondLetter);
                                builder.append(thirdLetter);
                                builder.append((regionCode < 10) ? padNumber(regionCode, 2) : regionCode);
                                builder.append('\n');

                                if (builder.length() > 1024) {
                                    writer.write(builder.toString());
                                    builder = new StringBuilder();
                                    writer.flush();
                                }
                            }
                        }
                    }
                }
                writer.write(builder.toString());
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String padNumber(int number, int numberLength) {
        StringBuilder builder = new StringBuilder(Integer.toString(number));
        for (int i = 0; i <= numberLength - builder.toString().length(); i++) {
            builder.insert(0, '0');
        }
        return builder.toString();
    }
}
