public class Main {

    public static void main(String[] args) {
        System.out.println(splitTextInToWords("Hello. world, lol"));
    }

    public static String splitTextInToWords(String text) {
        if (text.matches(".*[A-Za-z]+.*")){
            return text.replaceAll("(,|\\.|\\d+|;)", "")
                    .replaceAll("(\\s+|-)", "\n");
        } else
            return "";
    }

}
