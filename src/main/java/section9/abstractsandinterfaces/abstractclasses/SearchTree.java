package section9.abstractsandinterfaces.abstractclasses;

import java.util.List;

public class SearchTree implements NodeList {
    private ListItem root;

    public SearchTree(ListItem root) {
        this.root = root;
    }

    private void performRemoval(ListItem item, ListItem parent) {
        if (item.next() == null) {
            if (parent.next() == item) {
                parent.setNext(item.previous());
            } else if (parent.previous() == item) {
                parent.setPrevious(item.previous());
            } else {
                this.root = item.previous();
            }
        } else if (item.previous() == null) {
            if (parent.next() == item) {
                parent.setNext(item);
            } else if (parent.previous() == item) {
                parent.setPrevious(item.next());
            } else {
                this.root = item.previous();
            }
        } else {
            ListItem current = item.next();
            ListItem leftmostParent = item;
            while (current.previous() != null) {
                leftmostParent = current;
                current = current.previous();
            }
            item.setValue(current.getValue());
            if (leftmostParent == item) {
                item.setNext(current.next());
            } else {
                leftmostParent.setPrevious(current.next());
            }
        }
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
                    return true;
                }
            } else if (currentItem.compareTo(item) > 0) {
                if (currentItem.previous() != null) {
                    currentItem = currentItem.previous();
                } else {
                    currentItem.setPrevious(item);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean removeItem(ListItem item) {
        ListItem currentItem = root;
        ListItem parentItem = currentItem;
        while (currentItem != null) {
            if (currentItem.compareTo(item) < 0) {
                parentItem = currentItem;
                currentItem = currentItem.next();
            } else if (currentItem.compareTo(item) > 0) {
                parentItem = currentItem;
                currentItem = currentItem.previous();
            } else {
                performRemoval(currentItem, parentItem);
                return true;
            }
        }
        return false;
    }

    @Override
    public void traverse(ListItem root) {
        if (root != null) {
            traverse(root.previous());
            System.out.println(root.getValue());
            traverse(root.next());
        }
    }
}
