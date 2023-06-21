package academy.devgus.demo.controller;

import academy.devgus.demo.domain.Producer;
import academy.devgus.demo.request.ProducerPostRequest;
import academy.devgus.demo.response.ProducerPostResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping(path = {"v1/producers", "v1/producers/"})
public class ProducerController {

    //require a media json and consumes a json
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE,
        headers = "x-api-version=v1") //force the clients to pass a header
    public ResponseEntity<ProducerPostResponse> addProducer(@RequestBody ProducerPostRequest request) {
        var producer = Producer.builder()
                .name(request.getName())
                .id(ThreadLocalRandom.current().nextLong(100_000))
                .createdAt(LocalDateTime.now())
                .build();
        Producer.getProducers().add(producer);
        var response = ProducerPostResponse.builder()
                .name(producer.getName())
                .id(producer.getId())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
