package section19.databases.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SongInfo {
    private String title;
    private String album;
    private String artist;
    private int track;
}
