package rs.sbnz.book_recommender.dto.mapper;

import rs.sbnz.book_recommender.dto.BookDTO;
import rs.sbnz.book_recommender.model.Book;

import java.util.List;


public class BookMapper implements MapperInterface<Book, BookDTO>{
    @Override
    public Book toEntity(BookDTO dto) {
        return null;
    }

    @Override
    public List<Book> toEntityList(List<BookDTO> dtoList) {
        return null;
    }

    @Override
    public BookDTO toDto(Book entity) {
        return null;
    }

    @Override
    public List<BookDTO> toDtoList(List<Book> entityList) {
        return null;
    }
}
