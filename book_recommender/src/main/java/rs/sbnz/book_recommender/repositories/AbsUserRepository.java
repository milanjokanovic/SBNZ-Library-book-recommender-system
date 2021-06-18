package rs.sbnz.book_recommender.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import rs.sbnz.book_recommender.model.AbsUser;

public interface AbsUserRepository extends JpaRepository<AbsUser, Integer> {
    AbsUser findByEmail(String emailAddress);



    AbsUser findByUsername(String username);
}
