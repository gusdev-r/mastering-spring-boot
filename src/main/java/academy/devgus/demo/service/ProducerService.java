package academy.devgus.demo.service;

import academy.devgus.demo.domain.Producer;
import academy.devgus.demo.repository.ProducerHardCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProducerService {
    private final ProducerHardCodeRepository repository;

    public List<Producer> findAll(String name) {
        return repository.findByName(name);
    }
    public Producer save(Producer producer) {
        return repository.save(producer);
    }
    public Optional<Producer> findById (Long id) {
        return repository.findById(id);
    }
    public void delete(Long id) {
        var producer = findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Producer not found to be deleted"));
        repository.delete(producer);
    }
    public void update(Producer producerToUpdate) {
        var producer = findById(producerToUpdate.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Producer not found to be updated"));
        producerToUpdate.setCreatedAt(producer.getCreatedAt());
        repository.update(producer);
    }
}
