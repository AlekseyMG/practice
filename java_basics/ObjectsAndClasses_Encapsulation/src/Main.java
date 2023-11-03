import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        boolean testElevator = false;
        boolean testCargo = true;

        if (testElevator) {
            Elevator elevator = new Elevator(-3, 26);
            while (true) {
                System.out.print("Введите номер этажа: ");
                int floor = new Scanner(System.in).nextInt();
                elevator.move(floor);
            }
        }

        if (testCargo) {
            Cargo cargo = new Cargo(
                    "32A", "MCK",
                    false, true,
                    90, 60, 40, 30
            );
            Cargo firstCargoLink = cargo;
            cargo.printCargoLink(cargo, firstCargoLink);

            System.out.println("+++ Изменили вес +++");
            cargo = cargo.setCargoWeight(150);
            Cargo cargoLinkWithChangedWeight = cargo;
            cargo.printCargoLink(firstCargoLink, cargoLinkWithChangedWeight);

            System.out.println("+++ Изменили адрес +++");
            cargo = cargo.setDeliveryAdress("СПб");
            Cargo cargoLinkWithChangedDeliveryAdress = cargo;
            cargo.printCargoLink(cargoLinkWithChangedWeight, cargoLinkWithChangedDeliveryAdress);

            System.out.println("+++ Изменили ширину +++");
            cargo = cargo.setDimensionWidth(90);
            Cargo cargoLinkWithChangedWidth = cargo;
            cargo.printCargoLink(cargoLinkWithChangedDeliveryAdress, cargoLinkWithChangedWidth);

            System.out.println("+++ Изменили длину +++");
            cargo = cargo.setDimensionLength(170);
            Cargo cargoLinkWithChangedLength = cargo;
            cargo.printCargoLink(cargoLinkWithChangedWidth, cargoLinkWithChangedLength);

            System.out.println("+++ Изменили высоту +++");
            cargo = cargo.setDimensionHeight(200);
            Cargo cargoLinkWithChangedHeight = cargo;
            cargo.printCargoLink(cargoLinkWithChangedLength, cargoLinkWithChangedHeight);

            System.out.println("+++ Изменили ширину, высоту и длину +++");
            cargo = cargo.setDimensions(150, 130, 100);
            Cargo cargoLinkWithChangedDimensions = cargo;
            cargo.printCargoLink(cargoLinkWithChangedHeight, cargoLinkWithChangedDimensions);

            System.out.println("+++ Считаем объем +++");
            System.out.println("V = " + cargo.dimensionVolumeCalc());
        }

    }
}