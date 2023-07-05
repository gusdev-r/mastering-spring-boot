package academy.devgus.demo.repository;

import academy.devgus.demo.domain.Anime;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnimeData {
    private final List<Anime> animeList = new ArrayList<>();
    //static block
    {
         var onepiece = Anime.builder().name("One Piece").id(1L).build();
         var demonslayer = Anime.builder().name("Demons Slayer").id(2L).build();
         var attackontitan = Anime.builder().name("Attack on titan").id(3L).build();
         animeList.addAll(List.of(onepiece, demonslayer,attackontitan));
    }

    public List<Anime> getAnimes() {
        return animeList;
    }
}
