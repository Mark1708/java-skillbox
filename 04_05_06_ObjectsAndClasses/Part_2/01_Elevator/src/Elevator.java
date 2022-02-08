public class Elevator {
    private int currentFloor = 1;
    private int minFloor;
    private int maxFloor;

    public Elevator(int minFloor, int maxFloor) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void moveDown() {
        if (currentFloor - 1 < minFloor)
            System.out.println("Ниже только самооценка начинающего программиста");
        else {
            currentFloor--;
            System.out.println((currentFloor + 1) + " -> " + currentFloor);
        }
    }

    public void moveUp() {
        if (currentFloor + 1 > maxFloor)
            System.out.println("Бесконечность не предел");
        else {
            currentFloor++;
            System.out.println((currentFloor - 1) + " -> " + currentFloor);
        }
    }

    public void move(int floor) {
        if (floor > maxFloor || floor < minFloor)
            System.out.printf("Чтобы переместиться на %d этаж вам придется его достроить)\n", floor);
        else {
            while (floor != currentFloor) {
                if (floor < currentFloor)
                    moveDown();
                else
                    moveUp();
            }

        }
    }
}
