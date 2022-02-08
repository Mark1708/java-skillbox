import java.util.UUID;

public class Printer {
    private static String queue = "";
    private static int pendingPageCount = 0;
    private static int printedPageCount = 0;

    private String name;
    private String text;
    private int pageNum;

    public Printer(String name, String text, int pageNum) {
        this.name = name;
        this.text = text;
        this.pageNum = pageNum;
    }

    public Printer() {
    }

    public void append(String text) {
        append(UUID.randomUUID().toString(), text);
    }

    public void append(String name, String text) {
        append(name, text, 1);
    }

    public void append(String name, String text, int pageNum) {
        this.name = name;
        this.text = text;
        this.pageNum = pageNum;
        queue += (" - " + name + "\t" + text + "\t" + pageNum + "\n");
        pendingPageCount += pageNum;
    }

    public void print() {
        System.out.println(queue);
        printedPageCount += pendingPageCount;
        pendingPageCount = 0;
    }

    public void clear() {
        queue = "";
        pendingPageCount = 0;
    }

    public static int getPendingPageCount() {
        return pendingPageCount;
    }

    public static int getPrintedPageCount() {
        return printedPageCount;
    }

    public static String getQueue() {
        return queue;
    }
}
