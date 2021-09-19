package section12.collections.linkedsetsandmaps;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class StockList {
    private final Map<String, StockItem> items;

    public StockList() {
        this.items = new LinkedHashMap<>();
    }

    public int addStock(StockItem item) {
        if (item != null) {
            StockItem stockItem = items.getOrDefault(item.getName(), item);
            if (stockItem != item) {
                item.adjustStock(stockItem.getQuantity());
            }
            items.put(item.getName(), item);
            return item.getQuantity();
        }
        return 0;
    }

    public int sellStock(String itemName, int quantity) {
        StockItem stockItem = items.getOrDefault(itemName, null);
        if ((stockItem != null) && (stockItem.getQuantity() >= quantity) && quantity > 0) {
            stockItem.adjustStock(-quantity);
            return quantity;
        }
        return 0;
    }

    public StockItem get(String key) {
        return items.get(key);
    }

    public Map<String, StockItem> getItems() {
        return Collections.unmodifiableMap(items);
    }

    public int totalQuantity() {
        return items.values().stream()
                .map(StockItem::getQuantity)
                .reduce(0, Integer::sum);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("\nStock List\n");
        double totalCost = 0.0;
        for (Map.Entry<String, StockItem> entry : items.entrySet()) {
            StockItem item = entry.getValue();
            double value = item.getPrice() * item.getQuantity();
            s.append(item)
                    .append(" => Stock: ")
                    .append(item.getQuantity())
                    .append(", Total value: ")
                    .append(String.format("%.2f", value))
                    .append(", Reserved: ")
                    .append(item.getReserved())
                    .append(", Available: ")
                    .append(item.availableStock())
                    .append("\n");
            totalCost += value;
        }
        return s.append("Total items in stock: ")
                .append(totalQuantity())
                .append("\nTotal stock value: ")
                .append(String.format("%.2f", totalCost))
                .toString();
    }
}
