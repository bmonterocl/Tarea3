package backend;
public class BackendEvent {
    private String message;
    public BackendEvent(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}