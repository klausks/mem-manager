public class Main {
    public static void main(String[] args) throws InterruptedException {
        OS os = new OS();

        OS.createProcess(20);
        OS.createProcess(40);
        OS.createProcess(20);
        /*
        OS.createProcess(20000);
         */

        while (true) {
            OS.runProcesses();
            MemoryMonitor.printStatistics();
            MemoryMonitor.printPageTable();
            Thread.sleep(1000);
        }
    }
}
