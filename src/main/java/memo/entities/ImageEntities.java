package memo.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImageEntities {
    String imageTitle;
    String imageName;
    int imageScore;
}
