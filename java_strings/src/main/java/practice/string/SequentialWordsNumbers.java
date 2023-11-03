package practice.string;

public class SequentialWordsNumbers {

    public static String sequentialWordsNumbers(String text) {
        int numbers = 1;
        int startIndex = 0;
        int endIndex = 0;
        String changedText = "";

        endIndex = text.indexOf(' ', endIndex + 1);
        if (text.length() > 1 && endIndex > 0) {
            changedText += "(" + numbers + ") " + text.substring(startIndex, endIndex);
            numbers++;
        }
        if (text.length() > 1 && endIndex < 0) {
            changedText += "(" + numbers + ") " + text.substring(startIndex, text.length());
            numbers++;
        }
        while (endIndex >= 0) {
            startIndex = endIndex;
            endIndex = text.indexOf(' ', endIndex + 1);
            if (startIndex != endIndex && endIndex > 0 && startIndex > 0) {

                changedText += " (" + numbers + ")" + text.substring(startIndex, endIndex);
                numbers++;
            } else if (startIndex > endIndex) {
                changedText += " (" + numbers + ")" + text.substring(startIndex, text.length());
            }
        }
        return changedText;
    }
}
