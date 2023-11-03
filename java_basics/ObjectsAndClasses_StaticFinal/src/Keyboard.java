public class Keyboard {
    private final String type; //тип
    private final KeyboardBacklit backlit;//наличие подсветки
    private final int weight; //вес в граммах

    public Keyboard(String type, KeyboardBacklit backlit, int weight) {
        this.type = type;
        this.backlit = backlit;
        this.weight = weight;
    }
    public static Keyboard setKeyboard(String type, KeyboardBacklit backlit, int weight) {
        return new Keyboard(type, backlit, weight);
    }
    public Keyboard() {
        this.type = "N/A";
        this.backlit = KeyboardBacklit.NO;
        this.weight = 0;
    }

    public String getType() {
        return type;
    }

    public KeyboardBacklit isBacklit() {
        return backlit;
    }

    public int getWeight() {
        return weight;
    }
}
