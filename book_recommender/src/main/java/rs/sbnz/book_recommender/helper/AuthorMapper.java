package rs.sbnz.book_recommender.helper;

import rs.sbnz.book_recommender.dto.AuthorDTO;
import rs.sbnz.book_recommender.model.Author;

import java.util.List;
import java.util.stream.Collectors;

public class AuthorMapper implements MapperInterface<Author, AuthorDTO>{
    @Override
    public Author toEntity(AuthorDTO dto) {
        Author author = new Author();
        author.setName(dto.getName());
        author.setSystemGrade(dto.getSystemGrade());
        return author;
    }

    @Override
    public List<Author> toEntityList(List<AuthorDTO> dtoList) {
        return null;
    }

    @Override
    public AuthorDTO toDto(Author entity) {
        return new AuthorDTO(entity);
    }

    @Override
    public List<AuthorDTO> toDtoList(List<Author> entityList) {
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }
}
