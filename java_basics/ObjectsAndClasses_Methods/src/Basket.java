public class Basket {

    private static int count = 0;
    private static int totalItemsCount = 0;
    private static double totalItemsPrice = 0;
    private String items = "";
    private double totalPrice = 0;
    private int limit;
    private double totalWeight = 0;

    public double getTotalWeight() {
        return totalWeight;
    }
    public void add(String name, int price, int count, double weight){
        totalWeight = totalWeight + weight;
        add(name, price, count);
    }
     public Basket() {
        increaseCount(1);
        items = "Список товаров:";
        this.limit = 1000000;
    }

    public Basket(int limit) {
        this();
        this.limit = limit;
    }

    public Basket(String items, int totalPrice) {
        this();
        this.items = this.items + items;
        this.totalPrice = totalPrice;
    }

    public static int getCount() {
        return count;
    }

    public static int getTotalItemsCount() {
        return totalItemsCount;
    }

    public static double getTotalItemsPrice() {
        return totalItemsPrice;
    }

    public static void increaseCount(int count) {
        Basket.count = Basket.count + count;
    }

    public static void increaseTotalItemsCount(int count) {
        Basket.totalItemsCount = Basket.totalItemsCount + count;
    }

    public static void increaseTotalItemsPrice(double price) {
        Basket.totalItemsPrice = Basket.totalItemsPrice + price;
    }

    public static double totalAvgPrice() {
        return Basket.totalItemsPrice / Basket.totalItemsCount;
    }
    public static double totalAvgBasketPrice() {
        return Basket.totalItemsPrice / Basket.count;
    }

    public void add(String name, int price) {
        add(name, price, 1);
    }

    public void add(String name, int price, int count) {
        boolean error = false;
        if (contains(name)) {
            error = true;
        }

        if (totalPrice + count * price >= limit) {
            error = true;
        }

        if (error) {
            System.out.println("Error occured :(");
            return;
        }

        items = items + "\n" + name + " - " +
            count + " шт. - " + price + " = " + count * price;
        totalPrice = totalPrice + count * price;
        increaseTotalItemsCount(count);
        increaseTotalItemsPrice(count * price);
    }

    public void clear() {
        items = "";
        totalPrice = 0;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public boolean contains(String name) {
        return items.contains(name);
    }

    public void print(String title) {
        System.out.println(title);
        if (items.isEmpty()) {
            System.out.println("Корзина пуста");
        } else {
            System.out.println(items + "\n" + "Стоимость всех товаров в корзине: " + totalPrice + "\n");
        }
    }
}
