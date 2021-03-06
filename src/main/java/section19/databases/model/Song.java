package section19.databases.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Song {
    private int id;
    private int track;
    private String title;
    private int albumId;
}
