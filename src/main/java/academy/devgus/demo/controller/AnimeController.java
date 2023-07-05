package academy.devgus.demo.controller;

import academy.devgus.demo.mapper.AnimeMapper;
import academy.devgus.demo.request.AnimePostRequest;
import academy.devgus.demo.request.AnimePutRequest;
import academy.devgus.demo.response.AnimeGetResponse;
import academy.devgus.demo.response.AnimePostResponse;
import academy.devgus.demo.service.AnimeService;
import academy.devgus.demo.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = {"v1/animes", "v1/animes/"})
@Log4j2
@RequiredArgsConstructor

public class AnimeController {
    //    to use - aC -> aS -> aR
    private final AnimeMapper anime_mapper;
    private final AnimeService animeService;
        private DateUtil dateUtil;

        @GetMapping
        public ResponseEntity<List<AnimeGetResponse>> filterListByName (@RequestParam (required = false) String name) {
            log.info("Request received to list the anime list, param name '{}'", name);
            var animes = animeService.findAll(name);
            var response = anime_mapper.toListAnimeGetResponse(animes);

            return ResponseEntity.ok(response);
        }

        @GetMapping("{id}")
        public ResponseEntity<AnimeGetResponse> findById(@PathVariable Long id) {
            log.info("Request received, find the anime by id '{}'", id);
            var animeFound = animeService.findById(id);
//            var response = ANIME_MAPPER.toOptionalAnimeGetResponse(animeFound);
            var response = anime_mapper.toAnimeGetResponse(animeFound);
            return ResponseEntity.ok(response);
        }
        @PostMapping
        public ResponseEntity<AnimePostResponse> addAnime(@RequestBody AnimePostRequest request) {
            var anime = anime_mapper.toAnime(request);
            anime = animeService.save(anime);
            var response = anime_mapper.toAnimePostResponse(anime);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        @DeleteMapping("{id}")
        public ResponseEntity<Void> deleteAnimeById(@PathVariable Long id) {
            log.info("Request received to delete the anime by the id '{}'", id);
            animeService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        @PutMapping
        public ResponseEntity<Void> updateAnime(@RequestBody AnimePutRequest requestAnime) {
            log.info("Request receive to update the Anime '{}'", requestAnime);
            var anime = anime_mapper.toAnime(requestAnime);
            animeService.update(anime);

            return ResponseEntity.noContent().build();
        }
    }
