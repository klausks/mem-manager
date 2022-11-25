import java.util.ArrayList;
import java.util.List;

public class Process {

    private static int ID_COUNTER = 0;
    private long sizeInBytes;
    private int id;
    private List<Page> virtualAddressSpace;

    public Process(int sizeInBytes) {
        this.sizeInBytes = sizeInBytes;
        this.virtualAddressSpace = new ArrayList<>(sizeInBytes / Page.SIZE_KB);
        this.id = ID_COUNTER;
        ID_COUNTER++;
    }

    public void setVirtualAddressSpace(List<Page> pages) {
        this.virtualAddressSpace = pages;
    }

    public void run() {
        OS.accessMemory(getRandomPage());
    }

    private Page getRandomPage() {
        int randomIdx = (int) Math.round(Math.random() * (virtualAddressSpace.size() - 1));
        return virtualAddressSpace.get(randomIdx);
    }

    @Override
    public String toString() {
        return String.format("Process %d", id);
    }
}