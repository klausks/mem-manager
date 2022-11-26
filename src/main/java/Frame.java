public class Frame {
    private static int ID_COUNTER = 0;
    private int id;

    public Frame() {
        this.id = ID_COUNTER;
        ID_COUNTER++;
    }

    @Override
    public String toString() {
        return String.format("Frame %d", id);
    }

}
