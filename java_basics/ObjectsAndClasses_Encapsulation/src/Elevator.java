public class Elevator {
    private int minFloor = 0;
    private int maxFloor = 0;
    private int currentFloor = 1;

    public Elevator(int minFloor, int maxFloor) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
    }
    public int getCurrentFloor() {
        return currentFloor;
    }

    public void moveDown() {
        this.currentFloor = currentFloor - 1;
    }
    public void moveUp() {
        this.currentFloor = currentFloor + 1;
    }
    public void move(int floor) {
        boolean correctFloorSelected = floor >= minFloor && floor <= maxFloor && floor != currentFloor;
        if (floor > currentFloor && correctFloorSelected) {
            System.out.println("Едем вверх");
            for (int i = currentFloor; i != floor ; i++) {
                moveUp();
                System.out.println(getCurrentFloor() + " этаж");
            }
        } else if (floor < currentFloor && correctFloorSelected) {
            System.out.println("Едем вниз");
            for (int i = currentFloor; i != floor ; i--) {
                moveDown();
                System.out.println(getCurrentFloor() + " этаж");
            }
        } else {
            System.out.println("Неправильно указан этаж! Укажите от " + minFloor + " до " + maxFloor + ".");
        }
    }
}
