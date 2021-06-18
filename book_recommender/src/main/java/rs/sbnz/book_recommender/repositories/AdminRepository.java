package rs.sbnz.book_recommender.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.sbnz.book_recommender.model.Admin;
import rs.sbnz.book_recommender.model.User;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByEmail(String email);
    Admin findById(int id);
    Admin findByUsername(String username);
}
