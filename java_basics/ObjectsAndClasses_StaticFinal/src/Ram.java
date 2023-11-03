public class Ram {
    private final String type; //тип
    private final int size; //объём
    private final int weight; //вес в граммах

    public Ram(String type, int size, int weight) {
        this.type = type;
        this.size = size;
        this.weight = weight;
    }
    public Ram() {
        this.type = "N/A";
        this.size = 0;
        this.weight = 0;
    }
    public static Ram setRam(String type, int size, int weight) {
        return new Ram(type, size, weight);
    }

    public String getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    public int getWeight() {
        return weight;
    }


}
