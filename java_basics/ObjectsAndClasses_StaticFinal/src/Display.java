public class Display {
    private final DisplayType type; //тип (IPS, TN, VA)
    private final int size; //диагональ
    private final int weight; //вес в граммах

    public Display(DisplayType type, int size, int weight) {
        this.type = type;
        this.size = size;
        this.weight = weight;
    }
    public Display() {
        this.type = DisplayType.NULL;
        this.size = 0;
        this.weight = 0;
    }
    public static Display setDisplay(DisplayType type, int size, int weight) {
        return new Display(type, size, weight);
    }

    public DisplayType getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    public int getWeight() {
        return weight;
    }
}
