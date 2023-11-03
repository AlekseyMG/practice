package practice.twoDimensionalArray;

public class TwoDimensionalArray {

    public static final char SYMBOL = 'X';

    public static char[][] getTwoDimensionalArray(int size) {

        //TODO: Написать метод, который создаст двумерный массив char заданного размера.
        // массив должен содержать символ SYMBOL по диагоналям, пример для size = 3
        // [X,  , X]
        // [ , X,  ]
        // [X,  , X]
        char[][] twoDimensionalArray = new char[size][size];

        for (int i = 0, j = size - 1; i < size; i++, j--) {

            for (int k = 0; k < size; k++) {
                twoDimensionalArray[i][k] = ' ';
            }

            twoDimensionalArray[i][i] = SYMBOL;
            twoDimensionalArray[i][j] = SYMBOL;

        }
        return twoDimensionalArray;
    }
}
