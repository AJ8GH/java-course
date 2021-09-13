package section9.abstractsandinterfaces.abstractclasses;

public class Node extends ListItem {
    public Node(Object value) {
        super(value);
    }

    ListItem previous() {
        return leftLink;
    }

    ListItem setPrevious(ListItem leftLink) {
        return this.leftLink = leftLink;
    }

    ListItem next() {
        return rightLink;
    }

    ListItem setNext(ListItem rightLink) {
        return this.rightLink = rightLink;
    }

    int compareTo(ListItem item) {
        if (item != null) {
            return ((String) value).compareTo((String) item.getValue());
        }
        return -1;
    }
}
