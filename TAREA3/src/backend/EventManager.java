package backend;
import java.util.ArrayList;
import java.util.List;
public class EventManager {
    private static List<FrontendListener> listeners = new ArrayList<>();
    public static void subscribe(FrontendListener listener) {
        listeners.add(listener);
    }
    public static void notifyAll(BackendEvent event) {
        for (FrontendListener listener : listeners) {
            listener.onBackendEvent(event);
        }
    }
}