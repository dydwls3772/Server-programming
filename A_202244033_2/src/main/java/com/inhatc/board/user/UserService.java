package com.inhatc.board.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.inhatc.board.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public User create(String username, String email, String password) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        this.userRepository.save(user);
        return user;
    }
    
    public User get(String username) {
    	Optional<User> user= this.userRepository.findByUsername(username);
    	if(user.isPresent()) {
    	return user.get();
    	} else{
    		throw new DataNotFoundException("User not found.");
    	}
    }
}
