public abstract class Request {
    private long timeStamp;

    public long getTimeStamp() {
        return timeStamp;
    }

    public Request(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
