package academy.devgus.demo.service;

import academy.devgus.demo.domain.Producer;
import academy.devgus.demo.repository.ProducerHardCodeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.class)
class ProducerServiceTest {
    @InjectMocks
    private ProducerService producerService;
    @Mock
    private ProducerHardCodeRepository repository;

    private List<Producer> producerList;
    @BeforeEach
    void init() {
        var ufotable = Producer.builder().id(1L).name("Ufotable").createdAt(LocalDateTime.now()).build();
        var witStudio = Producer.builder().id(2L).name("Wit Studio").createdAt(LocalDateTime.now()).build();
        var studioGhibli = Producer.builder().id(3L).name("Studio Ghibli").createdAt(LocalDateTime.now()).build();
        producerList = new ArrayList<>(List.of(ufotable,witStudio,studioGhibli));
    }

//    BDDMockito - we verify by the repository with a simulation mock
//    Assertions - verify by the service

    @Test
    @DisplayName("findAll(), returns a list of producer when ")
    @Order(1)
    void findAll_ReturnsAllProducer_WhenSuccessful() {
        BDDMockito.when(repository.findByName(null)).thenReturn(this.producerList);

        var producers = producerService.findAll(null);
        Assertions.assertThat(producers).hasSameElementsAs(this.producerList);
    }

    @Test
    @DisplayName("findAll() returns a list with found producers when name is not null")
    @Order(2)
    void findAll_ReturnsFoundProducers_WhenNameIsPassedAndFound() {
        var name = "Ufotable";
//        producerFound is a list passed and verified at the assertions (its first position at the list)
        var producerFound = this.producerList.stream().filter(producer -> producer.getName().equals(name)).toList();
        BDDMockito.when(repository.findByName(name)).thenReturn(producerFound);

        var producers = producerService.findAll(name);
        Assertions.assertThat(producers).hasSize(1).contains(producerFound.get(0));
    }


    @Test
    @DisplayName("finAll() returns an empty list when no producer is found by name")
    @Order(3)
    void findAll_ReturnsEmptyList_WhenNoNameIsFound() {
        var name = "x";
        BDDMockito.when(repository.findByName(name)).thenReturn(Collections.emptyList());

        var producers = producerService.findAll(name);
        Assertions.assertThat(producers).isNotNull().isEmpty();
    }
    @Test
    @DisplayName("findById(), returns an optional producer when id exists")
    @Order(4)
    void findById_ReturnsAnOptional_WhenIdExists() {
        var id = 1L;
        var producerWait = this.producerList.get(0);
//        id 1 with the first producer in the list
        BDDMockito.when(repository.findById(id)).thenReturn(Optional.of(producerWait));

        var producerOptional = producerService.findById(id);
        Assertions.assertThat(producerOptional).isPresent().contains(producerWait);
    }

    @Test
    @DisplayName("findById(), returns empty optional when the id doesn't exists")
    @Order(5)
    void findById_ReturnsEmptyOptional_WhenIDDoesNotExists() {
        var id = 1L;
        BDDMockito.when(repository.findById(id)).thenReturn(Optional.empty());

        var producerOptional = producerService.findById(id);
        Assertions.assertThat(producerOptional).isEmpty();
    }
    @Test
    @DisplayName("save(), creates a producer")
    @Order(6)
    void save_CreatesProducer_WhenSuccessful() {
         var producerToSave = Producer.builder()
                 .name("MAPPA").id(17L)
                 .createdAt(LocalDateTime.now())
                 .build();
         BDDMockito.when(repository.save(producerToSave)).thenReturn(producerToSave);

         var producerSaveService = producerService.save(producerToSave);
         Assertions.assertThat(producerSaveService).isEqualTo(producerToSave)
                 .hasNoNullFieldsOrProperties();
    }
    @Test
    @DisplayName("delete(), removes a producer")
    @Order(7)
    void delete_RemovesAProducer_WhenSuccessful() {
        var id = 1L;
        var producerToDelete = producerList.get(0);
        BDDMockito.when(repository.findById(id)).thenReturn(Optional.of(producerToDelete));
        BDDMockito.doNothing().when(repository).delete(producerToDelete);

        Assertions.assertThatNoException().isThrownBy(() -> producerService.delete(id));
    }
    @Test
    @DisplayName("delete(), removes the producer and throw ResponseStatusException when producer is not found")
    @Order(8)
    void delete_ThrowsResponseStatusException_WhenNoProducerIsFound() {
        var id = 1L;
        BDDMockito.when(repository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThatException()
                .isThrownBy(() -> producerService.delete(id))
                .isInstanceOf(ResponseStatusException.class);
    }

    @Test
    @DisplayName("update(), update a producer when successful")
    @Order(9)
    void update_UpdateAProducer_WhenSuccessful() {
        var id = 1L;
        var producerToUpdate = this.producerList.get(0);
        producerToUpdate.setName("Aniplex");

        BDDMockito.when(repository.findById(id)).thenReturn(Optional.of(producerToUpdate));
        BDDMockito.doNothing().when(repository).update(producerToUpdate);

        Assertions.assertThatNoException().isThrownBy(() -> producerService.update(producerToUpdate));
    }
    @Test
    @DisplayName("update(), throws an ResponseStatusException when the producer is not found")
    @Order(10)
    void update_ThrowsResponseStatusException_WhenNoProducerIsFound() {
        var id = 1L;
        var producerToUpdate = this.producerList.get(0);
        producerToUpdate.setName("Aniplex");

        BDDMockito.when(repository.findById(id)).thenReturn(Optional.empty());
        Assertions.assertThatException().isThrownBy(() -> producerService.update(producerToUpdate))
                .isInstanceOf(ResponseStatusException.class);
    }
}
