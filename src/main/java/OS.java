import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OS {
    private static List<Process> processes = new ArrayList<>();

    public static void createProcess(int sizeInKb) {
        Process process = new Process(sizeInKb);
        if (!canAllocateVirtualMem(sizeInKb)) {
            System.err.println("Could not load process: not enough virtual memory available!");
            return;
        }
        int nOfRequiredPages = Math.round((float)sizeInKb / Page.SIZE_KB);
        List<Page> pages = MMU.allocateVirtualMem(nOfRequiredPages);

        process.setVirtualAddressSpace(pages);
        MemoryMonitor.virtualMemKbInUse += Page.SIZE_KB;

        processes.add(process);
        System.out.printf("%s with size of %d kB created\n", process, sizeInKb);
    }

    private static boolean canAllocateVirtualMem(int sizeInKb) {
        return (sizeInKb + MemoryMonitor.virtualMemKbInUse < MemoryMonitor.MAX_VIRTUAL_MEM_KB);
    }

    public static void runProcesses() {
        for (Process p : processes) {
            p.run();
        }
    }

    public static void runProcess(Process p) {
        p.run();
    }

    public static void accessMemory(Page page) {
        page.updateLastAccessAt();
        Optional<Frame> frameOptional = MMU.getFrame(page);
        if (frameOptional.isEmpty()) {
            System.out.printf("[PAGE FAULT] %s\n", page);
            if (MemoryMonitor.physicalMemKbInUse + Page.SIZE_KB > MemoryMonitor.MAX_PHYSICAL_MEM_KB) {
                swapIn(page);
            } else {
                MMU.allocateFrame(page);
                MemoryMonitor.physicalMemKbInUse += Page.SIZE_KB;
            }
        }
    }

    private static void swapIn(Page toBeSwappedIn) {
        swapOut();
        MMU.allocateFrame(toBeSwappedIn);
        System.out.printf("[SWAP-IN] %s", toBeSwappedIn);
        MemoryMonitor.swapIns++;
    }

    private static void swapOut() {
        Page toBeSwappedOut = MMU.getLeastRecentlyUsed();
        MMU.freeFrame(toBeSwappedOut);
        System.out.printf("[SWAP-OUT] %s\n", toBeSwappedOut);
        MemoryMonitor.swapOuts++;
    }
}
