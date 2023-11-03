package practice;

import java.util.*;

public class Main {
    /*
    TODO:
     - реализовать методы класса CoolNumbers
     - посчитать время поиска вводимого номера в консоль в каждой из структуры данных
     - проанализоровать полученные данные
     */
   // public static CoolNumbers coolNumbers = new CoolNumbers();
    public static void main(String[] args) {

        List<String> list = CoolNumbers.generateCoolNumbers();
        HashSet<String> hashSet = new HashSet<>(list);
        TreeSet<String> treeSet = new TreeSet<>(list);

        String carNumber = "Х999НН99";

        long now = System.currentTimeMillis();
        boolean founded = CoolNumbers.bruteForceSearchInList(list, carNumber);
        long elapsedTime = System.currentTimeMillis() - now;
        System.out.println("Поиск перебором: номер " + booleanFoundedToString(founded) +
                ", поиск занял " + elapsedTime + "нс");

        now = System.currentTimeMillis();
        founded = CoolNumbers.binarySearchInList(list, carNumber);
        elapsedTime = System.currentTimeMillis() - now;
        System.out.println("Бинарный поиск: номер " + booleanFoundedToString(founded) +
                ", поиск занял " + elapsedTime + "нс");

        now = System.currentTimeMillis();
        founded = CoolNumbers.searchInHashSet(hashSet, carNumber);
        elapsedTime = System.currentTimeMillis() - now;
        System.out.println("Поиск в HashSet: номер " + booleanFoundedToString(founded) +
                ", поиск занял " + elapsedTime + "нс");

        now = System.currentTimeMillis();
        founded = CoolNumbers.searchInTreeSet(treeSet, carNumber);
        elapsedTime = System.currentTimeMillis() - now;
        System.out.println("Поиск в TreeSet: номер " + booleanFoundedToString(founded) +
                ", поиск занял " + elapsedTime + "нс");
    }

    public static String booleanFoundedToString(boolean founded) {
        String foundedText;
        if (founded) {
            foundedText = "найден";
        } else {
            foundedText = "не найден";
        }
        return foundedText;
    }
}
