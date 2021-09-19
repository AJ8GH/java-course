package section12.collections.linkedsetsandmaps;

public class Main {
    private static final StockList stockList = new StockList();

    public static void main(String[] args) {
        createAndAdd("bread", 0.86, 100);
        createAndAdd("cake", 1.1, 7);
        createAndAdd("car", 12_500.0, 2);
        createAndAdd("chair", 62.0, 10);
        createAndAdd("cup", 5.0, 25);
        createAndAdd("cup", 4.0, 25);
        createAndAdd("door", 72.5, 6);
        createAndAdd("juice", 2.5, 80);
        createAndAdd("phone", 149.99, 55);
        createAndAdd("towel", 24.99, 72);
        createAndAdd("vase", 35, 30);

        System.out.println(stockList);

        Basket myBasket = new Basket("Adam's basket");

        addToBasket(myBasket, "car", 1);
        System.out.println(myBasket);

        addToBasket(myBasket, "car", 1);
        System.out.println(myBasket);

        addToBasket(myBasket, "car", 1);
        addToBasket(myBasket, "spanner", 1);
        addToBasket(myBasket, "cup", 11);
        addToBasket(myBasket, "door", 2);
        System.out.println(myBasket);
        System.out.println(stockList);

        checkout(myBasket);

        System.out.println(myBasket);

        System.out.println(stockList);

        System.out.println("====================");
        System.out.println("====================");
        System.out.println("====================");

        addToBasket(myBasket, "phone", 6);
        addToBasket(myBasket, "juice", 70);
        addToBasket(myBasket, "vase", 30);
        addToBasket(myBasket, "chair", 9);

        System.out.println(myBasket);
        System.out.println(stockList);

        checkout(myBasket);
        System.out.println(myBasket);
        System.out.println(stockList);

    }

    private static void createAndAdd(String name, double price, int quantity) {
        StockItem item = new StockItem(name, price, quantity);
        stockList.addStock(item);
    }

    private static int addToBasket(Basket basket, String item, int quantity) {
        StockItem stockItem = stockList.get(item);
        if (stockItem == null || stockItem.getQuantity() <= 0) {
            System.out.println(item + " not in stock");
            return 0;
        }
        basket.add(stockItem, quantity);
        return quantity;
    }

    private static void checkout(Basket basket) {
        basket.checkout(stockList);
    }
}
