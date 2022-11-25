import java.util.Map;

class MemoryMonitor {
    public static final int MAX_VIRTUAL_MEM_KB = 1000;
    public static final int MAX_PHYSICAL_MEM_KB = 64;
    public static int physicalMemKbInUse = 0;
    public static int virtualMemKbInUse = 0;
    public static int swapIns = 0;
    public static int swapOuts = 0;

    public static void printStatistics() {
        String header = "\n================ MEM STATISTICS ================\n";
        String stats = String.format(
                "Max. Virtual Memory: %d kB\nMax. Physical Memory: %d kB\nVirtual Memory Use: %.1f%%\nPhysical Memory Use: %.1f%%\nSwap Outs: %d\nSwap Ins: %d\n",
                MAX_VIRTUAL_MEM_KB, MAX_PHYSICAL_MEM_KB, getFreeVirtualMem(), getFreePhysicalMem(), swapOuts, swapIns
        );
        String footer = "================================================\n";
        System.out.println(header + stats + footer);
    }

    private static float getFreePhysicalMem() {
        return ((float) physicalMemKbInUse / (float) MAX_PHYSICAL_MEM_KB) * 100.0f;
    }

    private static float getFreeVirtualMem() {
        return ((float) virtualMemKbInUse / (float) MAX_VIRTUAL_MEM_KB) * 100.0f;
    }

    public static void printPageTable() {
        String header = String.format("%n%30s%40s%n", "Page", "Validity");
        StringBuilder sb = new StringBuilder();
        sb.append(header);
        for (Map.Entry<Page, Boolean> pageEntry: MMU.getPageTable().entrySet()) {
            sb.append(String.format("%s%15s%n", pageEntry.getKey(), pageEntry.getValue()));
        }
        System.out.println(sb);
    }
}
