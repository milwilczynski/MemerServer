package memo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IncreaseEntities {
    @JsonProperty(value = "token")
    String token;
    @JsonProperty(value = "name")
    String name;
}
