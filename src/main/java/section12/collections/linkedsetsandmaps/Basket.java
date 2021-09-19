package section12.collections.linkedsetsandmaps;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Basket {
    private final String name;
    private final Map<StockItem, Integer> items;

    public Basket(String name) {
        this.name = name;
        this.items = new TreeMap<>();
    }

    public int add(StockItem item, int quantity) {
        if (item != null && quantity > 0 && quantity <= item.availableStock()) {
            int total = items.getOrDefault(item, 0) + quantity;
            items.put(item, total);
            item.reserve(quantity);
            return total;
        }
        return 0;
    }

    public int remove(StockItem item, int quantity) {
        if (item != null && quantity > 0) {
            int total = items.get(item);
            if (total >= quantity) {
                if (total - quantity == 0) {
                    items.remove(item);
                } else {
                    items.put(item, total - quantity);
                }
                item.unReserve(quantity);
                return total - quantity;
            }
        }
        return 0;
    }

    public void checkout(StockList stockList) {
        Set<Map.Entry<StockItem, Integer>> itemSet = new HashSet<>(items.entrySet());
        for (Map.Entry<StockItem, Integer> item : itemSet) {
            stockList.sellStock(item.getKey().getName(), item.getValue());
            remove(item.getKey(), item.getValue());
        }
    }

    public Map<StockItem, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }

    public int totalQuantity() {
        return items.values().stream().reduce(0, Integer::sum);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder()
                .append("\nShoppingBasket ")
                .append(name)
                .append(" contains ")
                .append(totalQuantity())
                .append(totalQuantity() == 1 ? " item\n" : " items\n");

        double totalCost = 0.0;

        for (Map.Entry<StockItem, Integer> item : items.entrySet())  {
            s.append(item.getKey()).append(" => ")
                    .append(item.getValue())
                    .append(" reserved\n");
            totalCost += item.getKey().getPrice() * item.getValue();
        }
        return s.append("Total cost ").append(totalCost).toString();
    }
}
