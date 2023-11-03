package practice;

import java.util.*;

public class Main {
    public static PhoneBook phoneBook = new PhoneBook();
    public static void main(String[] args) {
        String inputText;

        while (true) {
            System.out.println("Введите номер, имя или команду:");
            inputText = new Scanner(System.in).nextLine();
            if (inputText.equals("0")) {
                break;
            }
            if (inputText.equals("LIST")) {
                for (String entry : phoneBook.getAllContacts()) {
                    System.out.println(entry);
                }

            } else if (phoneBook.checkName(inputText).contains(inputText)) {
                workWithName(inputText);
            } else if (phoneBook.checkPhone(inputText).contains(inputText)) {
                workWithPhone(inputText);
            } else {
                System.out.println("Неверный формат ввода");
            }
        }
    }

    public static void workWithName (String inputText) {
        if (phoneBook.getContactByName(inputText).toString().contains(inputText)) {
            System.out.println(phoneBook.getContactByName(inputText)
                    .toString().replaceAll("\\[*\\]*", ""));
        } else {
            String inputName = inputText;
            System.out.println("Такого имени в телефонной книге нет." + System.lineSeparator() +
                    "Введите номер телефона для абонента \"" + inputText + "\"");
            inputText = new Scanner(System.in).nextLine();
            if (phoneBook.checkPhone(inputText).contains(inputText)) {
                phoneBook.addContact(inputText, inputName);
            } else {
                System.out.println("Неверный формат ввода");
            }
        }
    }

    public static void workWithPhone (String inputText) {
        if (phoneBook.getContactByPhone(inputText).contains(inputText)) {
            System.out.println(phoneBook.getContactByPhone(inputText)
                    .replaceAll("\\[*\\]*", ""));
        } else {
            String inputPhone = inputText;
            System.out.println("Такого номера нет в телефонной книге." + System.lineSeparator() +
                    "Введите имя абонента для номера \"" + inputText + "\"");
            inputText = new Scanner(System.in).nextLine();
            if (phoneBook.checkName(inputText).toString().contains(inputText)) {
                phoneBook.addContact(inputPhone, inputText);
            } else {
                System.out.println("Неверный формат ввода");
            }
        }
    }
}
