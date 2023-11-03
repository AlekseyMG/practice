public class Cargo {
    private final Dimensions dimensions; //габариты;
    private final double weight; //масса;
    private final String deliveryAdress; //адрес доставки;
    private boolean onlyVertically = false; //свойство — можно ли переворачивать;
    private String regNo = ""; //регистрационный номер (может содержать буквы);
    private boolean fragile = false; //является ли груз хрупким.

    public Cargo(
            String regNo, String deliveryAdress,
            boolean onlyVertically, boolean fragile,
            double weight, double width, double height, double length
    ) {
        this.weight = weight;
        this.deliveryAdress = deliveryAdress;
        this.onlyVertically = onlyVertically;
        this.regNo = regNo;
        this.fragile = fragile;
        dimensions = new Dimensions(width, height, length);
    }

    public Cargo setCargoWeight(double weight) {
        return new Cargo(
                regNo, deliveryAdress,
                onlyVertically, fragile,
                weight, dimensions.getWidth(), dimensions.getHeight(), dimensions.getLength()
        );
    }
    public Cargo setDeliveryAdress(String deliveryAdress) {
        return new Cargo(
                regNo, deliveryAdress,
                onlyVertically, fragile,
                weight, dimensions.getWidth(), dimensions.getHeight(), dimensions.getLength()
        );
    }
    public Cargo setDimensions(double width, double height, double length) {
        return new Cargo(
                regNo, deliveryAdress,
                onlyVertically, fragile,
                weight, width, height, length
        );
    }
    public Cargo setDimensionWidth(double width) {
        return new Cargo(
                regNo, deliveryAdress,
                onlyVertically, fragile,
                weight, width, dimensions.getHeight(), dimensions.getLength()
        );
    }
    public Cargo setDimensionHeight(double height) {
        return new Cargo(
                regNo, deliveryAdress,
                onlyVertically, fragile,
                weight, dimensions.getWidth(), height, dimensions.getLength()
        );
    }
    public Cargo setDimensionLength(double length) {
        return new Cargo(
                regNo, deliveryAdress,
                onlyVertically, fragile,
                weight, dimensions.getWidth(), dimensions.getHeight(), length);
    }

    public double dimensionVolumeCalc() {
        return dimensions.volumeCalc();
    }
    public void printCargoLink (Cargo cargoLink1, Cargo cargoLink2) {
        System.out.println(
                "Регистрационный номер: " + cargoLink1.regNo + " -> " + cargoLink2.regNo + "\n" +
                "Адрес доставки: " + cargoLink1.deliveryAdress + " -> " + cargoLink2.deliveryAdress + "\n" +
                "Только вертикально: " + cargoLink1.onlyVertically + " -> " + cargoLink2.onlyVertically + "\n" +
                "Хрупкое: " + cargoLink1.fragile + " -> " + cargoLink2.fragile + "\n" +
                "Вес: " + cargoLink1.weight + " -> " + cargoLink2.weight + "\n" +
                "Ширина: " + cargoLink1.dimensions.getWidth() + " -> " + cargoLink2.dimensions.getWidth() + "\n" +
                "Высота: " + cargoLink1.dimensions.getHeight() + " -> " + cargoLink2.dimensions.getHeight() + "\n" +
                "Длина: " + cargoLink1.dimensions.getLength() + " -> " + cargoLink2.dimensions.getLength() + "\n"
        );
    }
}
