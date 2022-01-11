package section19.databases.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Artist {
    private int id;
    private String name;
}
