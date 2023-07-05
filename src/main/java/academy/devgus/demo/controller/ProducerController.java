package academy.devgus.demo.controller;

import academy.devgus.demo.mapper.ProducerMapper;
import academy.devgus.demo.request.ProducerPostRequest;
import academy.devgus.demo.request.ProducerPutRequest;
import academy.devgus.demo.response.ProducerGetResponse;
import academy.devgus.demo.response.ProducerPostResponse;
import academy.devgus.demo.service.ProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequestMapping(path = {"v1/producers", "v1/producers/"})
@RequiredArgsConstructor
public class ProducerController {
    private final ProducerMapper mapper;
    private final ProducerService producerService;

    
    @GetMapping
    public ResponseEntity<List<ProducerGetResponse>> filterListByName(@RequestParam(required = false) String name) {
        log.info("Request received to list the producer list, param name '{}'", name);
        var producers = producerService.findAll(name);
        var response = mapper.toListProducerGetResponse(producers);

        return ResponseEntity.ok(response);
    }
    
    //require a media json and consumes a json
    @PostMapping(produces = MediaType .APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE,
            headers = "x-api-version=v1") //force the clients to pass a header
    public ResponseEntity<ProducerPostResponse> addProducer(@RequestBody ProducerPostRequest request) {
        var producer = mapper.toProducer(request);
        producer = producerService.save(producer);

        var response = mapper.toProducerPostResponse(producer);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProducerById(@PathVariable Long id) {
        log.info("Request received to delete the producer with id '{}'", id);

        producerService.delete(id);

        return ResponseEntity.noContent().build();
    }
    @PutMapping
    public ResponseEntity<Void> updateProducer(@RequestBody ProducerPutRequest request) {
        log.info("Request received to update the producer '{}'", request);

        var producerToUpdate = mapper.toProducer(request);

        producerService.update(producerToUpdate);

        return ResponseEntity.noContent().build();
    }
}
