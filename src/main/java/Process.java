import java.util.ArrayList;
import java.util.List;

public class Process {
    private long sizeInBytes;
    private List<Page> virtualAddressSpace;

    public Process(int sizeInBytes) {
        this.sizeInBytes = sizeInBytes;
        this.virtualAddressSpace = new ArrayList<>(sizeInBytes / OS.PAGE_SIZE);
    }

    public void setVirtualAddressSpace(List<Page> pages) {
        this.virtualAddressSpace = virtualAddressSpace;
    }

    public void run() {
        OS.accessMemory(virtualAddressSpace.get(0));
    }


}