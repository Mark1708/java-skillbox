public class Main {

    public static void main(String[] args) {
        int volume = 1200;
        int fillingSpeed = 30; //30 litres per minute
        int devastationSpeed = 10; //10 litres per minute
        int currentVolume = 0;
        int time = 0;

        while (currentVolume < volume) {
            currentVolume += (fillingSpeed - devastationSpeed);
            time++;
        }

        System.out.printf("Feeling time = %d", time);
    }
}
