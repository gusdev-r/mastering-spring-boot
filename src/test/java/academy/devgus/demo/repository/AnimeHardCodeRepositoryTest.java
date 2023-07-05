package academy.devgus.demo.repository;

import academy.devgus.demo.domain.Anime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class AnimeHardCodeRepositoryTest {

    @InjectMocks
    private AnimeHardCodeRepository repository;
    @Mock
    private AnimeData animeData;
    private List<Anime> animeList;
    //findAll - sucessfull - when nothing is found
    //findByName - when nothing is found -
    //save - creates when sucessfull
    //delete - removes when sucessfull
    //update - when sucessfull

    @BeforeEach
    void init() {
        var onepiece = Anime.builder().name("One Piece").id(1L).build();
        var demonslayer = Anime.builder().name("Demons Slayer").id(2L).build();
        var attackontitan = Anime.builder().name("Attack on titan").id(3L).build();
        animeList.addAll(List.of(onepiece, demonslayer, attackontitan));

        BDDMockito.when(animeData.getAnimes()).thenReturn(animeList);
    }
    @Test
    @DisplayName("findAll() when is sucessfull")
    void findAll_ReturnsAllAnimes_Successful() {
        var animes = repository.findAll();
        Assertions.assertThat(animes).hasSize(3);
    }
    @Test
    @DisplayName("findByName() returns empty list when the anime isn't found")
    void findByName_ReturnsEmpty_WhenNothingIsFound() {
        var animes = repository.findByName("XXXXX");
        Assertions.assertThat(animes).isNotNull().isEmpty();
    }
    @Test
    @DisplayName("save(), creates an anime")
    void save_CreateAnime_WhenSuccessful() {
        var animeToSave = this.animeList.get(0);
        repository.save(animeToSave);
        Assertions.assertThat(this.animeList).contains(animeToSave);
    }
    @Test
    @DisplayName("delete(), delete an anime")
    void delete_RemoveAnAnime_WhenSuccessful() {
        var animeToDelete = this.animeList.get(0);
        repository.delete(animeToDelete);
        Assertions.assertThat(this.animeList).doesNotContain(animeToDelete);
    }
    @Test
    @DisplayName("update(), update an anime")
    void update_UpdateAnAnime_WhenSuccessful() {
        var animeToUpdate = this.animeList.get(0);
        animeToUpdate.setName("Tokyo Revengers");
        repository.update(animeToUpdate);

        Assertions.assertThat(this.animeList).contains(animeToUpdate);
        this.animeList.stream().filter(anime -> anime.getId().equals(animeToUpdate.getId()))
                .findFirst().ifPresent(anime -> Assertions.assertThat(anime.getName()).isEqualTo(animeToUpdate.getName()));
    }
    @Test
    @DisplayName("findById(), returns an object with given anime")
    void findById_ReturnsAnAnime_WhenSuccessful() {
        var animes = repository.findById(3L);
        Assertions.assertThat(animes).isPresent().contains(animeList.get(2));
    }
}