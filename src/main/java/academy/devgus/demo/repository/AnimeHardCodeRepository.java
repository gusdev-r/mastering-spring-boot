package academy.devgus.demo.repository;

import academy.devgus.demo.domain.Anime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import test.outside.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class AnimeHardCodeRepository {
    private AnimeData animeData;
    private static final List<Anime> ANIMES = new ArrayList<>();
    private final Connection connection;

    //static block
    static {
        var naruto = Anime.builder().name("Naruto").id(1L).build();
        var nanatsuNoTaizai = Anime.builder().name("Nanatsu no Taizai").id(2L).build();
        var tokyoGhoul =  Anime.builder().name("Tokyo Ghoul").id(3L).build();
        ANIMES.addAll(List.of(naruto, nanatsuNoTaizai, tokyoGhoul));
    }
    public List<Anime> findAll() {
        return ANIMES;
    }
    public Anime save(Anime anime) {
        ANIMES.add(anime);
        return anime;
    }
    public Optional<Anime> findById(Long id) {
        return ANIMES.stream().filter(anime -> anime.getId().equals(id)).findFirst();
    }
    public List<Anime> findByName(String name) {
        return ANIMES.stream().filter(anime -> anime.getName().equalsIgnoreCase(name)).toList();
    }
    public void delete(Anime anime) {
        ANIMES.remove(anime);
    }
    public void update(Anime anime) {
        delete(anime);
        save(anime);
    }
}
