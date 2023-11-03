public class Storage {
    private final StorageType type; //тип — HDD, SSD
    private final int size;//объём памяти
    private final int weight; //вес в граммах

    public Storage(StorageType type, int size, int weight) {
        this.type = type;
        this.size = size;
        this.weight = weight;
    }
    public Storage() {
        this.type = StorageType.HDD;
        this.size = 0;
        this.weight = 0;
    }
    public static Storage setStorage(StorageType type, int size, int weight) {
        return new Storage(type, size, weight);
    }

    public StorageType getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    public int getWeight() {
        return weight;
    }
}
