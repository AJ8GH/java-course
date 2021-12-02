package section9.abstractsandinterfaces.abstractclasses;

public class Node extends ListItem {
    public Node(Object value) {
        super(value);
    }

    @Override
    ListItem previous() {
        return leftLink;
    }

    @Override
    ListItem setPrevious(ListItem leftLink) {
        return this.leftLink = leftLink;
    }

    @Override
    ListItem next() {
        return rightLink;
    }

    @Override
    ListItem setNext(ListItem rightLink) {
        return this.rightLink = rightLink;
    }

    @Override
    int compareTo(ListItem item) {
        if (item != null) {
            return ((String) super.getValue()).compareTo((String) item.getValue());
        }
        return -1;
    }
}
