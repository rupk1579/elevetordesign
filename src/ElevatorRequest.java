import java.util.Objects;

public class ElevatorRequest extends Request {
    private int floorNumber;

    public ElevatorRequest(long timeStamp,int floorNumber) {
        super(timeStamp);
        this.floorNumber = floorNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElevatorRequest that = (ElevatorRequest) o;
        return this.getFloorNumber()==that.getFloorNumber();
    }
}
