package Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService; // constructor injection of the UserDetailsService, which is used to load user details for authentication and authorization.
        // see the user authentication and then allow the user to access the endpoint if they have the correct credentials.
    }

   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.POST, "/secure/admin/userAdded").permitAll()// allow anyone to access this endpoint to add a user
            .requestMatchers("/secure/admin/**").authenticated()// all other endpoints under "/secure/admin/**" require authentication
            .anyRequest().permitAll()
        )
        .httpBasic(Customizer.withDefaults())
        .userDetailsService(userDetailsService);

    return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
// this the class that has all the security configurations for our application.
// example, which endpoints are protected, which endpoints are public, which authentication method we are using, etc.
// In this case, we are using HTTP Basic authentication and we are allowing all users to access the "/secure/admin/userAdded" endpoint, but all other endpoints under "/rest/auth/**" and "/secure/admin/**" require authentication.