package academy.devgus.demo.mapper;

import academy.devgus.demo.domain.Anime;
import academy.devgus.demo.request.AnimePostRequest;
import academy.devgus.demo.request.AnimePutRequest;
import academy.devgus.demo.response.AnimeGetResponse;
import academy.devgus.demo.response.AnimePostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AnimeMapper {
    AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);

    //@Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "id", expression = "java(java.util.concurrent.ThreadLocalRandom.current().nextLong(100_000))")
    Anime toAnime(AnimePostRequest request);

    AnimePostResponse toAnimePostResponse(Anime anime);
    Anime toAnime(AnimePutRequest request);

    AnimeGetResponse toAnimeGetResponse(Anime anime);
    AnimeGetResponse toOptionalAnimeGetResponse(Optional<Anime> anime);

    List<AnimeGetResponse> toListAnimeGetResponse(List<Anime> anime);

}
