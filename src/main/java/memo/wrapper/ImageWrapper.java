package memo.wrapper;

import lombok.Getter;
import lombok.Setter;
import memo.entities.ImageEntities;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ImageWrapper {
    String name;
    List<ImageEntities> images = new ArrayList<>();
}
