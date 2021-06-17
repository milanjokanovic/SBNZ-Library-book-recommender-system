package rs.sbnz.book_recommender.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import rs.sbnz.book_recommender.model.User;
import rs.sbnz.book_recommender.repositories.BookRepository;
import rs.sbnz.book_recommender.repositories.UserRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;


    public List<User> findAll()
    {
        return userRepository.findAll();
    }

    public User addUser(User user) throws Exception
    {
        /*User activeUser = userRepository.findById(user.getId())
                .orElse(null);*/
        User activeUser = userRepository.findByEmail(user.getEmail());
        if(activeUser != null){
            throw new Exception();
        }
        user.setBlockedScoringFunction(0);
        user.setLastActive(new Date());
        userRepository.save(user);

        return user;
    }

    public Boolean deleteById(int userId) throws ResourceNotFoundException
    {
        User activeUser = userRepository.findById(userId)
                .orElse(null);
        if(activeUser == null)
            throw new ResourceNotFoundException("User not found for this id :: \" + userId");
        userRepository.delete(activeUser);
        /*Map< String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);*/
        return true;
    }

    public User updateUser(int userId, User user) // throws ResourceNotFoundException {
    {
        User activeUser = userRepository.findById(userId)
                .orElse(null); //Throw(() -> new ResourceNotFoundException("Employee not found for this id :: " + userId));

        activeUser.setEmail(user.getEmail());
        activeUser.setPassword(user.getPassword());

        final User updatedUser = userRepository.save(activeUser);

        return updatedUser;
    }
}
