package rs.sbnz.book_recommender.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.sbnz.book_recommender.model.Genre;

import java.util.List;
import java.util.Set;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
    Genre findByName(String name);

    @Query("SELECT genre FROM Genre genre WHERE genre.name IN (:names)")
    Set<Genre> findByGenreNames(@Param("names") List<String> names);
}
