package academy.devgus.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class Producer {
    private String name;
    private Long id;
    private LocalDateTime createdAt;

    private static List<Producer> producer = new ArrayList<>();

    //static block
    static {
        var mappa = Producer.builder().name("Mappa").id(1L).createdAt(LocalDateTime.now()).build();
        var kyotoAnimation = Producer.builder().name("Kyoto Animation").id(2L).createdAt(LocalDateTime.now()).build();
        var madhouse = Producer.builder().name("Madhouse").id(3L).createdAt(LocalDateTime.now()).build();
        producer.addAll(List.of(mappa, kyotoAnimation, madhouse));
    }
    public static List<Producer> getProducers() {
        return producer;
    }

}
