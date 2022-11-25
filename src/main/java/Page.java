import java.time.LocalDateTime;

public class Page {
    public static final int SIZE_KB = 8;
    public static int COUNTER = 0;
    private LocalDateTime lastAccessedAt;
    private int id;

    public Page() {
        lastAccessedAt = LocalDateTime.now();
        id = COUNTER;
        COUNTER++;
    }

    public LocalDateTime getLastAccessedAt() {
        return lastAccessedAt;
    }

    public void updateLastAccessAt() {
        lastAccessedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return String.format("Page %d - Last accessed at %s", id, lastAccessedAt);
    }
}
