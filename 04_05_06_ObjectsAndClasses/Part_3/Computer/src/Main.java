import component.*;

public class Main {

    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.setKeyboard(new Keyboard("Механическая", true, 400));
        computer.setProcessor(new Processor(2.4, 5, "Intel", 50));
        computer.setRam(new RAM("DDR3", 16, 80));
        computer.setScreen(new Screen(27, ScreenType.IPS, 2700));
        computer.setStorage(new Storage(StorageType.SSD, 512, 100));

        System.out.println(computer);
    }
}
