package section10;

import java.util.ArrayList;
import java.util.List;

public class League<T extends Team> {
    public String name;
    public List<T> league = new ArrayList<>();
}
