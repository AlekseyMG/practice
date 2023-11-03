package practice.reverseArray;

public class ReverseArray {

    public static String[] reverse(String[] strings) {
        //TODO: Напишите код, который меняет порядок расположения элементов внутри массива на обратный.
        String[] reverseStringsArray = strings.clone();
        int j = 0;

        for (int i = strings.length - 1; i >= 0; i--) {
            strings[j] = reverseStringsArray[i];
            j++;
        }
        return strings;
    }

}