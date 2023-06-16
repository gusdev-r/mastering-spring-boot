package academy.devgus.demo.Anime.ex.controller;

import academy.devgus.demo.Anime.ex.domain.Anime;
import academy.devgus.demo.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = {"v1/animes", "v1/animes/"})
@Log4j2
@AllArgsConstructor //Use require before to create a constructor for the final attributes
public class AnimeController {
private DateUtil dateUtil;
    @GetMapping()
    public List<Anime> listOfAnime() {
        log.info(dateUtil.formatLocalDateTimeToDataBaseStyle(LocalDateTime.now())); //each initialization will show
       return List.of(new Anime("Naruto", 699), new Anime("Nanatsu no Taizai", 346),
               new Anime("Tokyo Ghoul", 143));
    }
}
