package academy.devgus.demo.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class AnimePutRequest {
    private String name;
    private Long id;
}





