package academy.devgus.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Anime {
    @EqualsAndHashCode.Include
    @JsonProperty(value = "name") //all the values be referenced to name
    private String name;
    private Long id;

}


