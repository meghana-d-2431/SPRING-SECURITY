package Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Repository.UserRepository;
import springsecurity.Model.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user = repository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

    return new CustomUserDetails(user);
    }
}

//Spring receives username from browser/Postman
//it calls loadUserByUsername(username)
//this method searches DB using email because we are using email as username in our application. 
// So it calls repository.findByEmail(username)
//if user is found, it returns new CustomUserDetails(user)
//if not found, it throws UsernameNotFoundException