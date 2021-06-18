package rs.sbnz.book_recommender.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import rs.sbnz.book_recommender.model.AbsUser;
import rs.sbnz.book_recommender.model.User;
import rs.sbnz.book_recommender.model.VerificationToken;

public interface VerificationTokenRepository
        extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(AbsUser user);
}
