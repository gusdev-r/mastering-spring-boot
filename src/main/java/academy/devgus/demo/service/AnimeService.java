package academy.devgus.demo.service;

import academy.devgus.demo.domain.Anime;
import academy.devgus.demo.repository.AnimeHardCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AnimeService {
    private final AnimeHardCodeRepository repository;

    public List<Anime> findAll(String name) {
        return repository.findByName(name);
    }
    public Anime save(Anime anime) {
        return repository.save(anime);
    }
    public Anime findById (Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "The anime required can't be   found"));
    }
    public void delete(Long id) {
        var anime = findById(id);
        repository.delete(anime);
    }
    public void update(Anime animeToUpdate) {
       assertAnimeExists(animeToUpdate);
       repository.update(animeToUpdate);
    }
    private void assertAnimeExists(Anime animeTOUpdate) {
//        make sure there is an id or throw an exception
        findById(animeTOUpdate.getId());
    }
}
