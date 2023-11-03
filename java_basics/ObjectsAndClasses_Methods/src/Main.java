import java.math.RoundingMode;

public class Main {

    public static void main(String[] args) {

        for (int i = 0; i < 40; i = i + 10) {
            Basket basket = new Basket();
            basket.add("Milk", 40 + i, 10);
            basket.add("Bread", 10 + i, 5);
            basket.print("Milk");
        }

        System.out.println(
                "Всего корзин: " + Basket.getCount() + "\n" +
                "Всего товаров во всех корзинах: " + Basket.getTotalItemsCount() + "\n" +
                "Стоимость товаров во всех корзинах: " + Basket.getTotalItemsPrice() + "\n" +
                "Средняя цена товара во всех корзинах: " + Basket.totalAvgPrice() + "\n" +
                "Средняя цена корзины: " + Basket.totalAvgBasketPrice() + "\n"
        );

    }
}
