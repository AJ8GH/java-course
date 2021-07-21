package section9.abstractclasses;

public class MyLinkedList implements NodeList {
    private ListItem root;

    public MyLinkedList(ListItem root) {
        this.root = root;
    }

    @Override
    public ListItem getRoot() {
        return root;
    }

    @Override
    public boolean addItem(ListItem item) {
        ListItem currentItem = root;
        while(currentItem != null) {
            if (currentItem.compareTo(item) < 0) {
                if (currentItem.next() != null) {
                    currentItem = currentItem.next();
                } else {
                    currentItem.setNext(item);
                    item.setPrevious(currentItem);
                    return true;
                }
            } else if (currentItem.compareTo(item) > 0) {
                if (currentItem.previous() != null) {
                    currentItem = currentItem.previous();
                } else {
                    currentItem.setPrevious(item);
                    item.setNext(currentItem);
                    return true;
                }
            }
        }

        if (root.compareTo(item) == 0) return false;
        if (root.compareTo(item) == 1) {
            root.setPrevious(item);
        } else {
            root.setNext(item);
        }
        return true;
    }

    @Override
    public boolean removeItem(ListItem item) {
        ListItem currentItem = root;
        while (currentItem != null) {
            if (currentItem.compareTo(item) == 0) {

                return true;
            }
            currentItem = currentItem.next();
        }
        return false;
    }

    @Override
    public void traverse(ListItem root) {
    }
}
