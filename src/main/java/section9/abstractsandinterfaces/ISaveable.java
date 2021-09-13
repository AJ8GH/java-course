package section9.abstractsandinterfaces;

import java.util.List;

public interface ISaveable {
    List<String> write();
    void read(List<String> list);
}
