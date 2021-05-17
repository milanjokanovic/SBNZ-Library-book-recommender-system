package rs.sbnz.book_recommender.dto.mapper;

import rs.sbnz.book_recommender.dto.ScoreDTO;
import rs.sbnz.book_recommender.model.Score;

import java.util.List;

public class ScoreMapper implements MapperInterface<Score, ScoreDTO>{
    @Override
    public Score toEntity(ScoreDTO dto) {
        return null;
    }

    @Override
    public List<Score> toEntityList(List<ScoreDTO> dtoList) {
        return null;
    }

    @Override
    public ScoreDTO toDto(Score entity) {
        return null;
    }

    @Override
    public List<ScoreDTO> toDtoList(List<Score> entityList) {
        return null;
    }
}
