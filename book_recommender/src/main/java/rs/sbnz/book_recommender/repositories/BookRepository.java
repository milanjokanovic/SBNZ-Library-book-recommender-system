package rs.sbnz.book_recommender.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.sbnz.book_recommender.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
