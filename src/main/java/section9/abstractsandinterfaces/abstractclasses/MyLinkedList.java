package section9.abstractsandinterfaces.abstractclasses;

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
        if (root == null) {
            this.root = item;
            return true;
        }

        ListItem currentItem = root;
        while (currentItem != null) {
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
                    currentItem.previous().setNext(item);
                    item.setPrevious(currentItem.previous());
                    item.setNext(currentItem.previous());
                    currentItem.setPrevious(item);
                } else {
                    item.setNext(root);
                    this.root.setPrevious(item);
                    this.root = item;
                    return true;
                }
//                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean removeItem(ListItem item) {
        if (root == null) return false;

        ListItem currentItem = root;
        while (currentItem != null) {
            if (currentItem.compareTo(item) == 0) {
                if (currentItem == root) {
                    this.root = currentItem.next();
                } else {
                    currentItem.previous().setNext(currentItem.next());
                    if (currentItem.next() != null) {
                        currentItem.next().setPrevious(currentItem.previous());
                    }
                }
                return true;
            }
            if (currentItem.compareTo(item) < 0) {
                currentItem = currentItem.next();
            }
        }
        return false;
    }

    @Override
    public void traverse(ListItem root) {
        while (root != null) {
            System.out.println(root.getValue());
            root = root.next();
        }
    }
}
