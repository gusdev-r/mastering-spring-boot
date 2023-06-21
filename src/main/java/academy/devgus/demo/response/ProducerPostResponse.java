package academy.devgus.demo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public final class ProducerPostResponse {
    private String name;
    private Long id;

}
