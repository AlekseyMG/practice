package practice.regex;

import java.util.Scanner;

public class PhoneCleanerRegex {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine();
      if (input.equals("0")) {
        scanner.close();
        break;
      }

      // TODO:напишите ваш код тут, результат вывести в консоль.
      String regex = "[^0-9]";
      input = input.replaceAll(regex, "");
      boolean firstDigitsIsCorrect = input.charAt(0) == '7' || input.charAt(0) == '8' || input.charAt(0) == '9';
      boolean secondDigitsIsCorrect = input.charAt(1) == '9';

      if (input.length() == 10 && firstDigitsIsCorrect) {
        input = 7 + input.substring(0);
      } else if (input.length() == 11 && secondDigitsIsCorrect) {
        input = 7 + input.substring(1);
      } else {
        input = "Неверный формат номера";
      }
      System.out.println(input);
    }
  }

}
