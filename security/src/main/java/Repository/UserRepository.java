package Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import springsecurity.Model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    // When someone logs in, Spring needs to find that user in DB by email.This method does exactly that.
    Optional<User> findByEmail(String email); 
}