package practice;

import java.util.Scanner;

public class Main {
    private static TodoList todoList = new TodoList();
    public static String inputText;
    public static void main(String[] args) {
        // TODO: написать консольное приложение для работы со списком дел todoList


        while (true) {
            inputText = new Scanner(System.in).nextLine();
            if (inputText.length() > 0) {
                if (inputText.substring(0, 3).equals("ADD") && Character.isDigit(inputText.charAt(4))) {
                    int index = inputTextReplacementDigits("ADD ");
                      inputText = inputText.replace((String.valueOf(index)), "");
                      inputText = inputText.replace(" ", "");
                    if ((todoList.getTodos().size() >= index) && inputText.length() > 0) {
                        todoList.add(index, inputText);
                        System.out.println("Добавлено дело \"" + inputText + "\"");
                    }
                } else if (inputText.substring(0, 3).equals("ADD")) {
                    inputText = inputText.replace("ADD ", "");
                    todoList.add(inputText);
                    System.out.println("Добавлено дело \"" + inputText + "\"");
                } else if (inputText.substring(0, 4).equals("EDIT")) {
                    int index = inputTextReplacementDigits("EDIT ");
                    inputText = inputText.replace((String.valueOf(index)), "");
                    inputText = inputText.replace(" ", "");
                    if ((todoList.getTodos().size() >= index) && inputText.length() > 0) {
                        System.out.println("Дело \"" + todoList.getTodos().get(index) +
                                "\" заменено на \"" + inputText + "\"");
                        todoList.edit(index, inputText);
                    }
                } else if (inputText.substring(0, 4).equals("LIST")) {
                    printList();
                } else if (inputText.substring(0, 6).equals("DELETE")) {
                    inputText = inputText.replace("DELETE ", "");
                    int indexOfLastDigit = inputText.indexOf(' ');
                    if (indexOfLastDigit < 0) { indexOfLastDigit = 1;}
                    String digits = inputText.substring(0, indexOfLastDigit);
                    int index = Integer.valueOf(digits);
                    pushDelete(index);
                }
            }
        }
    }

    public static void printList() {
        if (todoList.getTodos().size() > 0) {
            int i = 0;
            for (String items : todoList.getTodos()) {
                System.out.println(i + " - " + items);
                i++;
            }
        }
    }

    public static void pushDelete(int index) {
        if (todoList.getTodos().size() >= index) {
            System.out.println("Дело \"" + todoList.getTodos().get(index) + "\" удалено");
            todoList.delete(index);
        } else {
            System.out.println("Дело с таким номером не существует");
        }

    }
    public static int inputTextReplacementDigits(String operator) {

        inputText = inputText.replace(operator, "");
        int indexOfLastDigit = inputText.indexOf(' ');
        if (indexOfLastDigit < 0) {
            indexOfLastDigit = 1;
        }
        String digits = inputText.substring(0, indexOfLastDigit);
        inputText = inputText.replace(digits, "");
        inputText = inputText.replace(" ", "");
        int index = Integer.valueOf(digits);
        return index;
    }
}
