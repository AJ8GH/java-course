package section12.collections.linkedsetsandmaps;

import java.util.Objects;

public class StockItem implements Comparable {
    private final String name;
    private double price;
    private int quantity;
    private int reserved;

    public StockItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public StockItem(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int availableStock() {
        return quantity - reserved;
    }

    public void reserve(int amount) {
        if (amount > 0 && amount <= quantity) {
            this.reserved += amount;
        }
    }

    public void unReserve(int amount) {
        if (amount > 0 && amount <= reserved) {
            this.reserved -= amount;
        }
    }

    public void adjustStock(int quantity) {
        int newQuantity = this.quantity + quantity;
        if (newQuantity >= 0) this.quantity = newQuantity;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (that != null && that.getClass() != that.getClass()) {
            return ((StockItem) that).getName().equals(name);
        }
        return false;
    }

    @Override
    public int compareTo(Object that) {
        System.out.println("Entering StockItem.compareTo()");
        if (this == that) return 0;
        if (that != null) return name.compareTo(((StockItem) that).getName());
        throw new NullPointerException();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return  name + ": price " + String.format("%.2f", price);
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getReserved() {
        return reserved;
    }

    public void setPrice(double price) {
        if (price > 0.0) this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
