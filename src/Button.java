public abstract class Button {

    private boolean illuminate;
    private int floorNumber;
    public void setIlluminate(){
        this.illuminate = true;
    }
    public void setNoIlluminate(){
        this.illuminate = false;
    }

    public Button(boolean illuminate, int floorNumber) {
        this.illuminate = illuminate;
        this.floorNumber = floorNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setIlluminate(boolean illuminate) {
        this.illuminate = illuminate;
    }
}
