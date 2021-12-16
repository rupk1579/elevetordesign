public class Door {
    private State state;

    public Door() {
        this.state = State.CLOSE;
    }

    public void openCloseDoor(){
        this.state =State.OPEN;
        System.out.println("Door opened...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.state =State.CLOSE;
        System.out.println("Door Closed...");
    }
}
