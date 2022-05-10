import parsers.DOMParser;
import parsers.OptimizeSAXParser;
import parsers.Parser;
import parsers.SAXParser;

/*
DOMParser  -  res/data-18M.xml
    Memory usage: 314465224 bytes	Duration: 2808 ms
    Memory usage: 149912776 bytes	Duration: 2010 ms
    Memory usage: 110415072 bytes	Duration: 1852 ms
SAXParser  -  res/data-18M.xml
    Memory usage: 321975640 bytes	Duration: 1716 ms
    Memory usage: 187580328 bytes	Duration: 1517 ms
    Memory usage: 371308872 bytes	Duration: 1467 ms
OptimizeSAXParser  -  res/data-18M.xml
    Memory usage: 283072088 bytes	Duration: 1597 ms
    Memory usage: 1088585496 bytes	Duration: 1703 ms
    Memory usage: 284396896 bytes	Duration: 1587 ms
*/
public class Loader {

    public static void main(String[] args) {
        String fileName = "res/data-18M.xml";

        test("DOMParser", fileName, 3);
        test("SAXParser", fileName, 3);
        test("OptimizeSAXParser", fileName, 3);


    }


    public static void test(String parserName, String fileName, int testCount) {
        Parser testParser = null;

        System.out.println(parserName + "  -  " + fileName);
        for (int i = 0; i < testCount; i++) {
            switch (parserName) {
                case "DOMParser":
                    testParser = new DOMParser(fileName);
                    break;
                case "SAXParser":
                    testParser = new SAXParser(fileName);
                    break;
                case "OptimizeSAXParser":
                    testParser = new OptimizeSAXParser(fileName);
                    break;
            }

            long start = System.currentTimeMillis();
            long usage = getUsage();
            testParser.parseFile();
            System.out.printf("Memory usage: %d bytes\tDuration: %d ms\n", Math.abs(getUsage() - usage), System.currentTimeMillis() - start);
        }

        //Printing results
//        testParser.printResults();
    }

    public static long getUsage() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }
}