import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

            System.out.println("Введите цифру:");
            int value = new Scanner(System.in).nextInt();
            int factorial = 1;

            for (int i = 1; i <= value; i = i + 1) {
                factorial = factorial * i;
            }
        System.out.println(value + "! = " + factorial);
    }
}