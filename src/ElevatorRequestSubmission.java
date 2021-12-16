import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ElevatorRequestSubmission {
  private static Set<ElevatorRequest>  elevatorRequest=new ConcurrentHashMap<>().newKeySet(15);
  //private static Set<Request>  floorRequest=new HashSet<>();;

  public ElevatorRequestSubmission() {
  }

  public static Set<ElevatorRequest> getElevatorRequest() {
    return elevatorRequest;
  }

  public void submitRequest(ElevatorRequest request){
      elevatorRequest.add(request);
  }
}
