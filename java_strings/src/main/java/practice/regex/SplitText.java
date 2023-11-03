package practice.regex;

public class SplitText {

  public static void main(String[] args) {

  }

  public static String splitTextIntoWords(String text) {
    //TODO реализуйте метод
    String regex = "[^A-Za-z ]";
    text = text.replaceAll(regex, "");
    StringBuilder changedText = new StringBuilder();
    int i;

    String[] words = text.split(" ");
    for(i = 0; i < words.length - 1; i++) {
      if (!words[i].isEmpty() && !words[i].isBlank()) {
        changedText.append(words[i]).append(System.lineSeparator());
      }
    }
    changedText.append(words[i]);
    return changedText.toString();
  }

}