import java.io.*;

public class Loader {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        int regionCode = 199;

        for (char firstLetter : letters) {
            new Thread(() -> {
                PrintWriter writer;
                try {
                    writer = new PrintWriter("res/numbers" + firstLetter +".txt");
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

                for (int number = 1; number < 1000; number++) {
                    StringBuilder carNumber = new StringBuilder();
                    for (char secondLetter : letters) {
                        for (char thirdLetter : letters) {
                            carNumber.append(firstLetter)
                                    .append(padNumber(number, 3))
                                    .append(secondLetter)
                                    .append(thirdLetter)
                                    .append(padNumber(regionCode, 2))
                                    .append('\n');
                        }
                    }
                    writer.write(carNumber.toString());
                }
                writer.flush();
                writer.close();
                System.out.println((System.currentTimeMillis() - start) + " ms");
            }).start();
        }
    }

    private static String padNumber(int number, int numberLength) {
        StringBuilder numberStr = new StringBuilder();
        numberStr.append(number);
        int padSize = numberLength - numberStr.length();

        for (int i = 0; i < padSize; i++) {
            numberStr.insert(0, 0);
        }
        return numberStr.toString();
    }
}
