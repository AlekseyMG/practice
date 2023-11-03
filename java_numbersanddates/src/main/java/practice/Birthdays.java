package practice;

import java.util.Locale;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;

public class Birthdays {

    public static void main(String[] args) {

        int day = 31;
        int month = 12;
        int year = 1990;

        System.out.println(collectBirthdays(year, month, day));

    }

    public static String collectBirthdays(int year, int month, int day) {

        //TODO реализуйте метод для построения строки в следующем виде
        //0 - 31.12.1990 - Mon
        //1 - 31.12.1991 - Tue
        String text = "";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(year, month, day);
        int i = 0;

        while (birthday.isBefore(today.plusDays(1))) {
            text =  text + i + " - " + dateFormatter.format(birthday) + " - " +
                    birthday.getDayOfWeek().getDisplayName(TextStyle.SHORT, new Locale("us")) +
                    System.lineSeparator();
            i++;
            birthday = birthday.plusYears(1);
        }
        return text;
    }
}
