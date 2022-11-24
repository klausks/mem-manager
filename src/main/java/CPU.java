import java.util.*;

public class CPU {
    public static List<Page> loadProcess(int sizeInBytes) {
        if (sizeInBytes + OS.virtualMemBytesInUse > OS.MAX_VIRTUAL_MEM) {
            System.err.println("Could not load process: not enough virtual memory available!");
        }
        int nOfRequiredPages = Math.round(sizeInBytes / OS.PAGE_SIZE);
        List<Page> pages = MMU.allocate(nOfRequiredPages);
        return pages;
    }

    public static Optional<Frame> loadPage() {

    }

    private static class MMU {
        private static Map<Page, Boolean> pageTable = new HashMap<>();
        private static Map<Page, Frame> pageFrameMap;

        public static List<Page> allocate(int nOfRequiredPages) {

            List<Page> pages = new ArrayList<>(nOfRequiredPages);
            while (nOfRequiredPages > 0) {
                Page newPage = new Page();
                pages.add(newPage);
                pageTable.put(newPage, false);
                OS.virtualMemBytesInUse += OS.PAGE_SIZE;
                nOfRequiredPages--;
            }
            return pages;
        }

        public static void updatePageTable() {
        }
    }


}
