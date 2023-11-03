package practice;

import java.util.*;

public class CoolNumbers {

    public static List<String> list = new ArrayList<>();
    public static String alfabet = "АВЕКМНОРСТУХ"; //0-11 12^3=1728*10*200=3_456_000
        //XNNNYZR
    public static int xIndex = 0;
    public static int yIndex = 0;
    public static int zIndex = 0;
    public static String stringX = String.valueOf(alfabet.charAt(xIndex));
    public static String stringY = String.valueOf(alfabet.charAt(yIndex));
    public static String stringZ = String.valueOf(alfabet.charAt(zIndex));

    public static int intN = 0;
    public static int intR = 0;
    public static StringBuilder carNumber = new StringBuilder();
    public static List<String> generateCoolNumbers() {
        for (int i = 0; i < 3_456_000; i++) {

            carNumber
                    .append(stringX)
                    .append(intN).append(intN).append(intN)
                    .append(stringY).append(stringZ)
                    .append(intR);
            list.add(carNumber.toString());
            carNumber.setLength(0);
            itrPP();
            if (xIndex == 12){
                break;
            }
        }
        return list;
    }

    public static void itrPP(){
        intR++;
        if (intR == 200){
            zIndex++;
            if (zIndex < 12){
                stringZ = String.valueOf(alfabet.charAt(zIndex));
            }
            intR = 0;
        }
        if (zIndex == 12){
            zIndex = 0;
            stringZ = String.valueOf(alfabet.charAt(zIndex));
            yIndex++;
            if (yIndex < 12){
                stringY = String.valueOf(alfabet.charAt(yIndex));
            }
        }
        if (yIndex == 12){
            yIndex = 0;
            stringY = String.valueOf(alfabet.charAt(yIndex));
            intN++;
        }
        if (intN == 10){
            xIndex++;
            if (xIndex < 12) {
                stringX = String.valueOf(alfabet.charAt(xIndex));
            }
            intN = 0;
        }
    }

    public static boolean bruteForceSearchInList(List<String> list, String number) {
        return list.contains(number);
    }

    public static boolean binarySearchInList(List<String> sortedList, String number) {
        return Collections.binarySearch(sortedList, number) != -1;
    }

    public static boolean searchInHashSet(HashSet<String> hashSet, String number) {
        return hashSet.contains(number);
    }

    public static boolean searchInTreeSet(TreeSet<String> treeSet, String number) {
        return treeSet.contains(number);
    }

}
