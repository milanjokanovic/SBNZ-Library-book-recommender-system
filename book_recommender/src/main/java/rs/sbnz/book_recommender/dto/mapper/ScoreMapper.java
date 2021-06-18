package rs.sbnz.book_recommender.dto.mapper;

import rs.sbnz.book_recommender.dto.ScoreDTO;
import rs.sbnz.book_recommender.model.Book;
import rs.sbnz.book_recommender.model.Score;
import rs.sbnz.book_recommender.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class ScoreMapper implements MapperInterface<Score, ScoreDTO>{
    @Override
    public Score toEntity(ScoreDTO dto) {
        Score score = new Score();
        score.setValue(dto.getValue());

        Book book = new Book();
        book.setTitle(dto.getBookTitle());
        score.setBook(book);

        User user = new User();
        user.setId(dto.getUserId());
        score.setUser(user);

        return score;
    }

    @Override
    public List<Score> toEntityList(List<ScoreDTO> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public ScoreDTO toDto(Score entity) {
        ScoreDTO dto = new ScoreDTO(entity.getId(), entity.getUser().getId(),
                entity.getBook().getTitle(), entity.getValue());
        return dto;
    }

    @Override
    public List<ScoreDTO> toDtoList(List<Score> entityList) {

        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }
}
