
import java.util.HashMap;
import java.util.Map;

public class SmartWarehouseLocator {

    static class Location {
        int x;
        int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "[Aisle " + x + ", Rack " + y + "]";
        }
    }

    static class WarehouseGrid {
        private final int gridWidth;
        private final int gridLength;
        
        private final Map<String, Location> inventoryMap;

        public WarehouseGrid(int width, int length) {
            this.gridWidth = width;
            this.gridLength = length;
            this.inventoryMap = new HashMap<>();
        }

        public boolean storeItem(String sku, int x, int y) {
            if (x < 0 || x >= gridWidth || y < 0 || y >= gridLength) {
                System.out.println("Error: Location (" + x + "," + y + ") is out of grid bounds!");
                return false;
            }
            inventoryMap.put(sku, new Location(x, y));
            System.out.println("Stored item " + sku + " at " + inventoryMap.get(sku));
            return true;
        }

        public Location locateItem(String sku) {
            return inventoryMap.get(sku);
        }

        public int calculateTravelDistance(Location start, Location end) {
            if (start == null || end == null) return -1;
            return Math.abs(start.x - end.x) + Math.abs(start.y - end.y);
        }
    }

    public static void main(String[] args) {
        WarehouseGrid warehouse = new WarehouseGrid(100, 100);
        Location loadingDock = new Location(0, 0);

        System.out.println("--- Warehousing Items ---");
        warehouse.storeItem("SP-01", 12, 45);
        warehouse.storeItem("SP-02", 50, 10);
        warehouse.storeItem("SP-03", 5, 88);

        System.out.println("\n--- Locating Item ---");
        String searchSku = "SKU-1002";
        Location foundLocation = warehouse.locateItem(searchSku);

        if (foundLocation != null) {
            System.out.println("Item " + searchSku + " found at: " + foundLocation);
            
            int distance = warehouse.calculateTravelDistance(loadingDock, foundLocation);
            System.out.println("Distance from Loading Dock (0,0): " + distance + " grid units.");
        } else {
            System.out.println("Item " + searchSku + " not found in inventory.");
        }
    }
}
