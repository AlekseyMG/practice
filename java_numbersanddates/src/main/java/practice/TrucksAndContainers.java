package practice;

import java.util.Scanner;

public class TrucksAndContainers {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //получение количество коробок от пользователя
        int boxes = scanner.nextInt();

        // TODO: вывести в консоль коробки разложенные по грузовикам и контейнерам
        // пример вывода при вводе 2
        // для отступа используйте табуляцию - \t

        /*
        Грузовик: 1
            Контейнер: 1
                Ящик: 1
                Ящик: 2
        Необходимо:
        грузовиков - 1 шт.
        контейнеров - 1 шт.
        */

        int trucks = 0;
        int containers = 0;
        int containersInLastTuck = 0;
        int maxContainersInTuck = 12;
        int maxBoxesInContainer = 27;
        int boxesCount = 1;
        String text = "";


        if (boxes > 0) {
            trucks++;
            containers++;
            containersInLastTuck++;
            text += "Грузовик: " + trucks + System.lineSeparator();
            text += "\tКонтейнер: " + containers + System.lineSeparator();
            for (int i = 1; i <= boxes; i++) {
                text += "\t\tЯщик: " + i + System.lineSeparator();
                boxesCount++;
                if (boxesCount > maxBoxesInContainer && boxes != i){
                    boxesCount = 1;
                    containers++;
                    containersInLastTuck++;
                    if (containersInLastTuck <= maxContainersInTuck) {
                        text += "\tКонтейнер: " + containers + System.lineSeparator();
                    }
                }
                if (containersInLastTuck > maxContainersInTuck) {
                    trucks++;
                    containersInLastTuck = 1;
                    text += "Грузовик: " + trucks + System.lineSeparator();
                    text += "\tКонтейнер: " + containers + System.lineSeparator();
                }
            }
        }

        System.out.println(text +
                "Необходимо:" + System.lineSeparator() +
                "грузовиков - " + trucks + " шт." + System.lineSeparator() +
                "контейнеров - " + containers + " шт."
        );
    }

}
