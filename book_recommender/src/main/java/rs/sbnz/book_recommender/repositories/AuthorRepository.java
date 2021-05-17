package rs.sbnz.book_recommender.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.sbnz.book_recommender.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Author findByName(String name);
}
