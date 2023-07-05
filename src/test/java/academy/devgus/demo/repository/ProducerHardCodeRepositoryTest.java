package academy.devgus.demo.repository;

import academy.devgus.demo.domain.Producer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProducerHardCodeRepositoryTest {
        @InjectMocks //works as autowired
    private ProducerHardCodeRepository repository;
        @Mock
        private ProducerData producerData;
        private List<Producer> producerList;

        @BeforeEach //to each test start with a new list
        //static block
        void init() {
            var ufotable = Producer.builder().name("Mappa").id(1L).createdAt(LocalDateTime.now()).build();
            var withStudio = Producer.builder().name("Kyoto Animation").id(2L).createdAt(LocalDateTime.now()).build();
            var studioGhibi = Producer.builder().name("Madhouse").id(3L).createdAt(LocalDateTime.now()).build();
            producerList.addAll(List.of(ufotable, withStudio, studioGhibi));

            BDDMockito.when(producerData.getProducers()).thenReturn(producerList);
//            by mock, when the getProducers was called, it'll return the producerList
        }
        @Test
        @Order(1)
    @DisplayName("findAll() returns a list with all producers" )
    void findAll_ReturnsAllProducers_WhenSuccessful() {
            var producers = repository.findAll();
            Assertions.assertThat(producers).hasSize(3); //have sure the size is three
        }
        @Test
        @Order(2)
        @DisplayName("findByName() returns empty list when the producer isn't found")
    void findByName_ReturnsEmptyListOfProducer_WhenNothingIsFound() {
            var producers = repository.findByName("XXXXX");
            Assertions.assertThat(producers).isNotNull().isEmpty();
    }
    @Test
    @Order(3)
    @DisplayName("save(), creates a producer")
    void save_CreatesAProducer_WhenSuccessful() {

        var producerToUpdate = producerList.get(0);
        repository.save(producerToUpdate);
        Assertions.assertThat(this.producerList).contains(producerToUpdate);

//            Producer producerToBeSaved = Producer.builder()
//                    .name("MAPPA").id(1L).createdAt(LocalDateTime.now()).build();
//            var producer = repository.save(producerToBeSaved);
//            Assertions.assertThat(producer).isEqualTo(producerToBeSaved).hasNoNullFieldsOrProperties();
//            var producers = repository.findAll();
//            Assertions.assertThat(producers).contains(producerToBeSaved);
    }

    @Test
    @Order(3)
    @DisplayName("delete(), removes a producer")
    void delete_RemovesAProducer_WhenSuccessful(){
            var producerToDelete = producerList.get(0);
            repository.delete(producerToDelete);

            Assertions.assertThat(this.producerList).doesNotContain(producerToDelete);
    }
    @Test
    @Order(4)
    @DisplayName("update(), update a producer")
    void update_UpdateAProducer_WhenSuccessful() {
            var producerToUpdate = this.producerList.get(0);
            producerToUpdate.setName("Aniplex");
            repository.update(producerToUpdate);

        Assertions.assertThat(this.producerList).contains(producerToUpdate);
        this.producerList.stream().filter(p -> p.getId().equals(producerToUpdate.getId()))
                .findFirst().ifPresent(p -> Assertions.assertThat(p.getName()).isEqualTo(producerToUpdate.getName()));
    }
}