import java.util.List;

public class Elevator {
    private  Integer id;
    private  Direction direction;
    private  Status status;
    private static final int numOfFloor=10;
    private int currentFloor;
    private List<ElevatorButton> buttonList;
    private ElevatorRequest destinationFloor;
    private Door door;

    public Elevator(List<ElevatorButton> elevatorButtons,Door door) {
        this.buttonList = elevatorButtons;
        this.direction = Direction.NONE;
        this.status = Status.HELD;
        this.door = door;
        this.currentFloor =1;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    public void StopElevator(){
        this.status=Status.WAITING;
    }

    public ElevatorRequest getDestinationFloor() {
        return destinationFloor;
    }

    public void setDestinationFloor(ElevatorRequest destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    public Door getDoor() {
        return door;
    }

    public List<ElevatorButton> getButtonList() {
        return buttonList;
    }
}
