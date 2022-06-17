package net.java.authService.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import net.java.authService.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	
    User findByUserNameAndPassword(String username, String password);
    
    User findByUserName(String username);


}
