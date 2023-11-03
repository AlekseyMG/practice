public class Cpu {
    private final String vendor; //производитель
    private final double speed; //частота
    private final int cores; //количество ядер
    private final int weight; //вес в граммах

    public Cpu(String vendor, double speed, int cores, int weight) {
        this.vendor = vendor;
        this.speed = speed;
        this.cores = cores;
        this.weight = weight;
    }
    public Cpu() {
        this.vendor = "N/A";
        this.speed = 0;
        this.cores = 0;
        this.weight = 0;
    }

    public static Cpu setCpu(String vendor, double speed, int cores, int weight) {
        return new Cpu(vendor, speed, cores, weight);
    }

    public String getVendor() {
        return vendor;
    }

    public double getSpeed() {
        return speed;
    }

    public int getCores() {
        return cores;
    }

    public int getWeight() {
        return weight;
    }
}
