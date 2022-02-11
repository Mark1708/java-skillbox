import component.*;

public class Computer {

    private Processor processor;
    private RAM ram;
    private Storage storage;
    private Screen screen;
    private Keyboard keyboard;

    public Computer(Processor processor, RAM ram, Storage storage, Screen screen, Keyboard keyboard) {
        this.processor = processor;
        this.ram = ram;
        this.storage = storage;
        this.screen = screen;
        this.keyboard = keyboard;
    }

    public Computer() {
    }

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public RAM getRam() {
        return ram;
    }

    public void setRam(RAM ram) {
        this.ram = ram;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    @Override
    public String toString() {
        return "Computer {\n" +
                " processor=" + processor +
                ",\n ram=" + ram +
                ",\n storage=" + storage +
                ",\n screen=" + screen +
                ",\n keyboard=" + keyboard +
                "\n}";
    }
}
