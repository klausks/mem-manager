import java.util.Map;

public class MMU {
    private Map<Page, Boolean> virtualPageTable;
    private Map<Page, Frame> mapping;
}
