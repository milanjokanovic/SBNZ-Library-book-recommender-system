package rs.sbnz.book_recommender.helper;

import rs.sbnz.book_recommender.dto.GenreDTO;
import rs.sbnz.book_recommender.model.Genre;

import java.util.List;
import java.util.stream.Collectors;

public class GenreMapper implements MapperInterface<Genre, GenreDTO>{
    @Override
    public Genre toEntity(GenreDTO dto) {
        Genre genre = new Genre();
        genre.setName(dto.getName());
        genre.setSystemGrade(dto.getSystemGrade());
        return genre;
    }

    @Override
    public List<Genre> toEntityList(List<GenreDTO> dtoList) {
        return null;
    }

    @Override
    public GenreDTO toDto(Genre entity) {
        return new GenreDTO(entity);
    }

    @Override
    public List<GenreDTO> toDtoList(List<Genre> entityList) {
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }
}
