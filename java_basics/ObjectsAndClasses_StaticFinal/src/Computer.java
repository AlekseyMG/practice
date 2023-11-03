public class Computer {
//    Метод расчёта общей массы компьютера, возвращающий суммарный вес всех его комплектующих.
//    Геттеры и сеттеры для всех комплектующих компьютера.
//    Метод toString, возвращающий в удобочитаемом формате всю информацию о компьютере и его комплектующих.
    private final String vendor;
    private final String name;
    private Cpu cpu;
    private Ram ram;
    private Storage storage;
    private Display display;
    private Keyboard keyboard;

    public Computer(String vendor, String name) {
        this.vendor = vendor;
        this.name = name;
        cpu = new Cpu();
        ram = new Ram();
        storage = new Storage();
        display = new Display();
        keyboard = new Keyboard();
    }
    public Computer() {
        this.vendor = "N/A";
        this.name = "N/A";
    }

    public Cpu getCpu() {
        return cpu;
    }

    public void setCpu(String vendor, double speed, int cores, int weight) {
        this.cpu = Cpu.setCpu(vendor, speed, cores, weight);
    }

    public Ram getRam() {
        return ram;
    }

    public void setRam(String type, int size, int weight) {
        this.ram = Ram.setRam(type, size, weight);
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(StorageType type, int size, int weight) {
        this.storage = Storage.setStorage(type, size, weight);
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(DisplayType type, int size, int weight) {
        this.display = Display.setDisplay(type, size, weight);
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(String type, KeyboardBacklit backlit, int weight) {
        this.keyboard = Keyboard.setKeyboard(type, backlit, weight);
    }
    public int getFullWeight(){
        return cpu.getWeight() + ram.getWeight() + storage.getWeight() + display.getWeight() + keyboard.getWeight();
    }
    @Override
    public String toString() {
        return "Computer: " + "\n" +
                "Vendor: " + vendor + "\n" +
                "Name: " + name + "\n" +
                "CPU: " + cpu.getVendor() + " " + cpu.getSpeed() + "GHz " + cpu.getCores() + " cores" + "\n" +
                "RAM: " + ram.getType() + " " + ram.getSize() + "Gb" + "\n" +
                "Storage: " + storage.getType() + " " + storage.getSize() + "Gb" + "\n" +
                "Display: " + display.getType() + " " + display.getSize() + "\"" + "\n" +
                "Keyboard: " + keyboard.getType() + " / With backlit: " + keyboard.isBacklit() + "\n" +
                "Weight: " + getFullWeight() + "g\n";
    }
}
