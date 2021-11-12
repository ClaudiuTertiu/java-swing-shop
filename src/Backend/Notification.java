package Backend;

import java.time.LocalDateTime;

public class Notification {
    private NotificationType notificationType;
    private LocalDateTime send;
    private int ID;
    private enum NotificationType {
        EDIT,
        CANCEL
    }

    public Notification(NotificationType notificationType, LocalDateTime send, int ID) {
        this.notificationType = notificationType;
        this.send = send;
        this.ID = ID;
    }

    public Notification() {
    }
}
