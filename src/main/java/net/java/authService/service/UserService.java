package net.java.authService.service;


import net.java.authService.Exception.UserAlreadyExistsException;
import net.java.authService.Exception.UserNotFoundException;
import net.java.authService.Exception.ValidationException;
import net.java.authService.domain.ResetPasswordRequest;
import net.java.authService.domain.User;

public interface UserService {
    
    User register(User user) throws UserAlreadyExistsException;

    User login(String email, String password) throws UserNotFoundException;
    
    User fetchUserByUsername(String username) throws UserNotFoundException;
    
    User updateUser(User user) throws ValidationException;
    
    void resetPassword(ResetPasswordRequest user) throws ValidationException;

}
