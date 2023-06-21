package academy.devgus.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Anime {
    private String name;
    private Long id;
    private int chapters;
    private static List<Anime> animes = new ArrayList<>();

    //static block
    static {
        var naruto = new Anime("Naruto", 1L, 1);
        var nanatsuNoTaizai = new Anime("Nanatsu no Taizai", 2L, 2);
        var tokyoGhoul = new Anime("Tokyo Ghoul", 3L, 3);
        animes.addAll(List.of(naruto, nanatsuNoTaizai, tokyoGhoul));
    }
    public static List<Anime> getAnimes() { //to make right, override the get
        return animes;
    }
}
