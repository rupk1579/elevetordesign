import java.util.Set;

public class ElevatorController implements Runnable {

    private Elevator elevator;

    public ElevatorController(Elevator elevator) {
        this.elevator = elevator;
    }

    @Override
    public void run() {
        if(elevator.getStatus().equals(Status.RUNNING)){
            System.out.println("Elevator Running..");
            ElevatorRequest request=elevator.getDestinationFloor();
            if(elevator.getCurrentFloor() == request.getFloorNumber()){
                System.out.println("Elevator Reached floor:"+request.getFloorNumber());
                elevator.getDoor().openCloseDoor();
                ElevatorRequestSubmission.getElevatorRequest().remove(request);
                System.out.println("Request Processed:"+request.getFloorNumber());
                elevator.setStatus(Status.WAITING);
                ElevatorRequest nextRequest = setNextRequest();
                if(nextRequest != null){
                    elevator.setDestinationFloor(nextRequest);
                    elevator.setStatus(Status.RUNNING);
                }
                else{
                   elevator.setStatus(Status.HELD);
                }
            }
            else{
                ElevatorRequest request1 =  containRequest();
                if(request1 != null){
                    elevator.setDestinationFloor(request1);
                }

            }
        }else if(elevator.getStatus().equals(Status.HELD)){
            System.out.println("Elevator is held");
              ElevatorRequest request = setNextRequest();

              if(request!=null){
                  System.out.println("Next Request:"+request.getFloorNumber());

                  elevator.getDoor().openCloseDoor();
                  elevator.setStatus(Status.RUNNING);
                  elevator.setDestinationFloor(request);
              }
        }
        if(elevator.getDestinationFloor() !=null && elevator.getCurrentFloor()>elevator.getDestinationFloor().getFloorNumber()){
            elevator.setCurrentFloor(elevator.getCurrentFloor()-1);
        }else if(elevator.getDestinationFloor() !=null && elevator.getCurrentFloor()<elevator.getDestinationFloor().getFloorNumber())
            elevator.setCurrentFloor(elevator.getCurrentFloor()+1);
       // elevator.setCurrentFloor(elevator.getDirection().equals(Direction.UP) ? elevator.getCurrentFloor() +1 :elevator.getCurrentFloor() -1);
    }
    private ElevatorRequest containRequest(){
        for(ElevatorRequest request: ElevatorRequestSubmission.getElevatorRequest()){
            int floorNumber = request.getFloorNumber();
            if(floorNumber <elevator.getDestinationFloor().getFloorNumber()) return request;
        }
        return null;
    }
    private ElevatorRequest setNextRequest(){

        if(elevator.getDirection()==Direction.UP){
            ElevatorRequest request = getUpWardRequest();
            return  request !=null ? request : getDownWardRequest();
        }else if(elevator.getDirection() == Direction.DOWN){
            ElevatorRequest request = getDownWardRequest();
            return request!= null ? request : getUpWardRequest();
        }
        else {
            return getPriorityRequest();
        }

    }
    private ElevatorRequest getUpWardRequest(){
        for(ElevatorRequest request: ElevatorRequestSubmission.getElevatorRequest()){
            int floorNumber = request.getFloorNumber();
            if(floorNumber >elevator.getCurrentFloor()) return request;
        }
        return null;
    }
    private ElevatorRequest getDownWardRequest(){
        for(ElevatorRequest request: ElevatorRequestSubmission.getElevatorRequest()){
            int floorNumber = request.getFloorNumber();
            if(floorNumber < elevator.getCurrentFloor()) return request;
        }
        return null;
    }
    private ElevatorRequest getPriorityRequest(){
        ElevatorRequest elevatorRequest=null;
        long timeMin = Long.MAX_VALUE;
        for(ElevatorRequest request: ElevatorRequestSubmission.getElevatorRequest()){
            System.out.println("Request:"+request.getFloorNumber()+",");
            if(timeMin>request.getTimeStamp()){
                timeMin = request.getTimeStamp();
                elevatorRequest = request;
            }

        }
        if (elevatorRequest == null) return  null;
        if(elevatorRequest.getFloorNumber()<elevator.getCurrentFloor())
            elevator.setDirection(Direction.DOWN) ;
        else
        elevator.setDirection(Direction.UP);
        return elevatorRequest;

    }
}
