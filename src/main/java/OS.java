import java.util.List;

public class OS {
    public static final int MAX_VIRTUAL_MEM = 1000000;
    public static final int MAX_PHYSICAL_MEM = 64000;

    public static int physicalMemBytesInUse = 0;
    public static int framesInuse = 0;
    public static int pagesInUse = 0
    public static int virtualMemBytesInUse = 0;

    public static final int PAGE_SIZE = 8000;
    private static CPU cpu;
    private static List<Process> processes;

    public static void createProcess(int sizeInBytes) {
        Process newProcess = new Process(sizeInBytes);
        List<Page> pages = CPU.loadProcess(sizeInBytes);
        newProcess.setVirtualAddressSpace(pages);
        processes.add(newProcess);
    }

    public static boolean accessMemory(Page page) {
        CPU.loadPage(page);
    }

    public static void swapPage(Integer pageNum, Page newPage) {

    }



}
