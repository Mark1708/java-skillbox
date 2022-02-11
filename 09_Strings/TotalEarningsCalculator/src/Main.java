public class Main {

    public static void main(String[] args) {
        System.out.println(calculateSalarySum("Коля заработал 50000 рублей, Федя - 45800 рубля, а Саша - 23000 рублей"));
    }

    public static int calculateSalarySum(String text){
        //TODO: реализуйте метод
        if (!text.matches(".*\\d+.*"))
            return 0;
        else {
            String[] arrNum = text
                    .replaceAll("[^0-9]", " ")
                    .trim()
                    .split("\\s+");
            int sum = 0;
            for (String num : arrNum) {
                sum += Integer.parseInt(num);
            }
            return sum;
        }
    }

}
