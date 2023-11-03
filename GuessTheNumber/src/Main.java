import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int value = (int) (Math.random() * 100);
        while (true) {
            System.out.println("Введите число:");
            int attempt = new Scanner(System.in).nextInt();
            if (value < attempt){
                System.out.println("Загаданное число меньше");
            }
            if (value > attempt){
                System.out.println("Загаданное число больше");
            }
            if (value == attempt){
                System.out.println("Вы угадали!");
                break;
            }
        }
    }
}