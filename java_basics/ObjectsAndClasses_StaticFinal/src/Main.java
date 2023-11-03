public class Main {
    public static void main(String[] args) {
        Computer computer1 = new Computer("Asus", "Monster");
        computer1.setCpu("Intel", 3.4, 8, 150);
        computer1.setRam("DDR4", 16, 100);
        computer1.setStorage(StorageType.SSD, 1000, 130);
        computer1.setDisplay(DisplayType.IPS, 27, 1500);
        computer1.setKeyboard("Mechanical", KeyboardBacklit.YES, 350);

        Computer computer2 = new Computer("MSI", "Beginner");
        computer2.setCpu("Intel", 2.0, 2, 130);
        computer2.setRam("DDR3", 4, 70);
        computer2.setStorage(StorageType.HDD, 750, 500);
        computer2.setDisplay(DisplayType.TN, 19, 1700);
        computer2.setKeyboard("Mechanical", KeyboardBacklit.NO, 350);

        Computer computer3 = new Computer("AMD", "Office");
        computer3.setCpu("AMD", 3.2, 4, 170);
        computer3.setRam("DDR4", 8, 90);
        computer3.setStorage(StorageType.SSD, 500, 130);
        computer3.setDisplay(DisplayType.VA, 24, 1400);
        computer3.setKeyboard("Mechanical", KeyboardBacklit.NO, 350);

        System.out.println(computer1.toString());
        System.out.println(computer2.toString());
        System.out.println(computer3.toString());
    }
}
