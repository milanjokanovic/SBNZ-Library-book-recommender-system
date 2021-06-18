package rs.sbnz.book_recommender.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rs.sbnz.book_recommender.model.AbsUser;
import rs.sbnz.book_recommender.model.VerificationToken;
import rs.sbnz.book_recommender.repositories.AbsUserRepository;
import rs.sbnz.book_recommender.repositories.VerificationTokenRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AbsUserRepository absUserRepository;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AbsUser user = absUserRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));

        return user;
    }

    public AbsUser loadUserById(Integer id) {
        AbsUser user = absUserRepository.findById(id).orElse(null);
        return user;
    }

    public AbsUser getUser(String verificationToken) {
        AbsUser user = tokenRepository.findByToken(verificationToken).getUser();
        return user;
    }

    public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }

    public void saveRegisteredUser(AbsUser user) {
        absUserRepository.save(user);
    }

    public VerificationToken createVerificationToken(AbsUser user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        return tokenRepository.save(myToken);
    }
}
