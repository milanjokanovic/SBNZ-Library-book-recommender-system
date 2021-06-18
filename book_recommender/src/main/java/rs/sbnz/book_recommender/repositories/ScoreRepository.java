package rs.sbnz.book_recommender.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.sbnz.book_recommender.model.Book;
import rs.sbnz.book_recommender.model.Score;
import rs.sbnz.book_recommender.model.User;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Integer> {
    Score findByUserAndBook(User user, Book book);
}
