package academy.devgus.demo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProducerGetResponse {
    private String name;
    private Long id;
}
