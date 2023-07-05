package academy.devgus.demo.repository;

import academy.devgus.demo.domain.Producer;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProducerData {
    private final List<Producer> producers = new ArrayList<>();
    //static block
     {
        var mappa = Producer.builder().name("Mappa").id(1L).createdAt(LocalDateTime.now()).build();
        var kyotoAnimation = Producer.builder().name("Kyoto Animation").id(2L).createdAt(LocalDateTime.now()).build();
        var madhouse = Producer.builder().name("Madhouse").id(3L).createdAt(LocalDateTime.now()).build();
        producers.addAll(List.of(mappa, kyotoAnimation, madhouse));
    }

    public List<Producer> getProducers() {
        return producers;
    }
}
