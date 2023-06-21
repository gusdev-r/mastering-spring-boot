package academy.devgus.demo.controller;

import academy.devgus.demo.domain.Anime;
import academy.devgus.demo.util.DateUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping(path = {"v1/animes", "v1/animes/"})
@Log4j2
public class AnimeController {
    private DateUtil dateUtil;
        @GetMapping
        public List<Anime> filterListByName (@RequestParam (required = false) String name) throws JsonProcessingException {
            log.info("Request received to list the anime list, param name '{}'", name);
            var animes = Anime.getAnimes();
            if (name == null) return animes;
            return animes.stream().filter(anime -> anime.getName().equalsIgnoreCase(name)).toList();
        }
        @GetMapping("{id}")
        public Anime findById(@PathVariable Long id) {
            log.info("Request received, find the anime by id '{}'", id);
            return Anime.getAnimes()
                    .stream()
                    .filter(anime -> anime.getId().equals(id))
                    .findFirst().orElse(null);
        }
        @PostMapping
        public Anime addAnime(@RequestBody Anime anime) {
            anime.setId(ThreadLocalRandom.current().nextLong(100_000));
            Anime.getAnimes().add(anime);
            return anime;
        }
}
