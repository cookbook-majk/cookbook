package cookbook.cookbookrecipeapplication.services;

import cookbook.cookbookrecipeapplication.models.User;
import cookbook.cookbookrecipeapplication.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDaoService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDaoService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userRepository.save(user);
    }
}
