package rs.sbnz.book_recommender.dto.mapper;

import rs.sbnz.book_recommender.dto.BookDTO;
import rs.sbnz.book_recommender.model.Author;
import rs.sbnz.book_recommender.model.Book;
import rs.sbnz.book_recommender.model.Genre;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class BookMapper implements MapperInterface<Book, BookDTO>{
    @Override
    public Book toEntity(BookDTO dto) {
        Book book = new Book();

        book.setTitle(dto.getTitle());
        book.setBrPregleda(dto.getViewNumber());
        book.setPageNum(dto.getPageNum());
        book.setSeries(dto.getSeries());
        book.setSeriesNumber(dto.getSeriesNumber());
        book.setTargetAudience(dto.getTargetAudience());
        book.setYearOfPublishing(dto.getYearOfPublishing());
        book.setBasedOnRealEvent(dto.getBasedOnRealEvent());
        book.setNobelPrize(dto.getNobelPrize());

        Author author = new Author();
        author.setName(dto.getAuthorName());

        book.setAuthor(author);

        Set<Genre> genreSet = new HashSet<>();
        for(String genreName : dto.getGenres()){
            Genre genre = new Genre();
            genre.setName(genreName);

            genreSet.add(genre);
        }

        book.setGenres(genreSet);

        return book;
    }

    @Override
    public List<Book> toEntityList(List<BookDTO> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public BookDTO toDto(Book entity) {
        List<String> genres = new ArrayList<>();
        for(Genre genre : entity.getGenres()){
            genres.add(genre.getName());
        }

        BookDTO bookDTO = new BookDTO(
                entity.getId(), entity.getPageNum(), entity.getBrPregleda(),
                entity.getTitle(), entity.getSeries(), entity.getSeriesNumber(),
                entity.getTargetAudience(), entity.getBasedOnRealEvent(), entity.getNobelPrize(),
                entity.getYearOfPublishing(), entity.getAuthor().getName(), genres, entity.avgGrade()
        );

        return bookDTO;
    }

    @Override
    public List<BookDTO> toDtoList(List<Book> entityList) {
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }
}
