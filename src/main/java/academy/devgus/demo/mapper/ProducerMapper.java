package academy.devgus.demo.mapper;

import academy.devgus.demo.domain.Producer;
import academy.devgus.demo.request.ProducerPostRequest;
import academy.devgus.demo.request.ProducerPutRequest;
import academy.devgus.demo.response.ProducerGetResponse;
import academy.devgus.demo.response.ProducerPostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProducerMapper {
    ProducerMapper INSTANCE = Mappers.getMapper(ProducerMapper.class);

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "id", expression = "java(java.util.concurrent.ThreadLocalRandom.current().nextLong(100_000))")
    Producer toProducer(ProducerPostRequest request);

    ProducerPostResponse toProducerPostResponse(Producer producer);
    List<ProducerGetResponse> toListProducerGetResponse(List<Producer> producers);

    Producer toProducer (ProducerPutRequest request);
}

