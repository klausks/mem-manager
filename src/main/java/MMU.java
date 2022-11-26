import java.time.LocalDateTime;
import java.util.*;

class MMU {
    private static Map<Page, Boolean> pageTable = new HashMap<>();
    private static Map<Page, Frame> pageFrameMap = new HashMap<>();

    public static Map<Page, Boolean> getPageTable() {
        return pageTable;
    }
    public static List<Page> allocateVirtualMem(int nOfRequiredPages) {

        List<Page> pages = new ArrayList<>(nOfRequiredPages);
        while (nOfRequiredPages > 0) {
            Page newPage = new Page();
            pages.add(newPage);
            pageTable.put(newPage, false);
            nOfRequiredPages--;
        }
        return pages;
    }

    public static void allocateFrame(Page page, Frame frame) {
        if (frame == null) {
            frame = new Frame();
        }
        pageFrameMap.put(page, frame);
        pageTable.put(page, true);
        System.out.printf("[ALLOCATE] %s to %s\n", frame, page);
    }

    public static Optional<Frame> getFrame(Page page) {
        boolean isPageInPhysicalMemory = pageTable.get(page);
        if (isPageInPhysicalMemory) {
            Frame frame = pageFrameMap.get(page);
            System.out.printf("[RETRIEVE] %s -> %s\n", frame, page);
            return Optional.of(pageFrameMap.get(page));
        } else {
            return Optional.empty();
        }
    }

    public static Page getLeastRecentlyUsed() {
        LocalDateTime leastRecentlyUsedAt = LocalDateTime.of(9999, 1, 1, 0, 0);
        Page leastRecentlyUsed = null;
        for (Page pageEntry: pageFrameMap.keySet()) {
            if (pageEntry.getLastAccessedAt().isBefore(leastRecentlyUsedAt)) {
                leastRecentlyUsed = pageEntry;
                leastRecentlyUsedAt = pageEntry.getLastAccessedAt();
            }
        }
        return leastRecentlyUsed;
    }

    public static Frame freeFrame(Page page) {
        Frame freed = pageFrameMap.get(page);
        pageFrameMap.remove(page);
        pageTable.put(page, false);
        return freed;
    }
}
