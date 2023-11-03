public class Dimensions {
    private final double width; //широта
    private final double height; //высота
    private final double length; //длина
    public Dimensions(double width, double height, double length) {
        this.width = width;
        this.height = height;
        this.length = length;
    }
    public Dimensions() {
        this.width = 0;
        this.height = 0;
        this.length = 0;
    }
    public Dimensions setDimensions(double width, double height, double length) {
        return new Dimensions(width, height, length);
    }
    public Dimensions setDimensionWidth(double width) {
        return new Dimensions(width, height, length);
    }
    public Dimensions setDimensionHeight(double height) {
        return new Dimensions(width, height, length);
    }
    public Dimensions setDimensionLength(double length) {
        return new Dimensions(width, height, length);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getLength() {
        return length;
    }

    public double volumeCalc() {
        return width * height * length;
    }
}
