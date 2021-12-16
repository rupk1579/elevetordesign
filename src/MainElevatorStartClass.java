import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainElevatorStartClass {
    public static void main(String[] args) {
        //Initialize ElevatorButton
        List<ElevatorButton> elevatorButtons = new ArrayList<>();
        for(int i=1;i<=5;i++){
            elevatorButtons.add(new ElevatorButton(false,i));
        }
        //Initialize Elevator
        Elevator elevator = new Elevator(elevatorButtons,new Door());
        //Starting scheduled thread to process request.
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(new ElevatorController(elevator),0,500, TimeUnit.MILLISECONDS);
        //Place ConcurrentRequest .
       ElevatorRequestSubmission elevatorRequestSubmission = new ElevatorRequestSubmission();
       Random random = new Random();
       for(int i=1;i<=10;i++){
           int floorNumber = random.nextInt(10)+1;
           ElevatorRequest elevatorRequest = new ElevatorRequest(System.currentTimeMillis(),floorNumber);
           elevatorRequestSubmission.submitRequest(elevatorRequest);
           System.out.println("Request Submit:"+floorNumber);
           try {
               Thread.sleep(1000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }

       }

    }
}

