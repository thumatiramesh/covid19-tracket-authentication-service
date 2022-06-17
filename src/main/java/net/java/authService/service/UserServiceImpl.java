package net.java.authService.service;


import org.springframework.stereotype.Service;


import net.java.authService.Exception.UserAlreadyExistsException;
import net.java.authService.Exception.UserNotFoundException;
import net.java.authService.Exception.ValidationException;
import net.java.authService.domain.ResetPasswordRequest;
import net.java.authService.domain.User;
import net.java.authService.repository.UserRepository;



@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) throws UserAlreadyExistsException {
        User existingUser = userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
        if (existingUser != null) {
            throw new UserAlreadyExistsException();
        }
        return userRepository.save(user);
    }

    @Override
    public User login(String userName, String password) throws UserNotFoundException {
        User user = userRepository.findByUserNameAndPassword(userName, password);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;

    }

	@Override
	public User fetchUserByUsername(String username) throws UserNotFoundException {
		User user = userRepository.findByUserName(username);
		return user;
	}

	@Override
	public User updateUser(User user) throws ValidationException {
		User existingUser = userRepository.findByUserName(user.getUserName());
		if(existingUser != null) {
			existingUser.setFirstName(user.getFirstName());
			existingUser.setLastName(user.getLastName());
			existingUser.setEmail(user.getEmail());
			existingUser = userRepository.save(existingUser);
		}else {
			throw new ValidationException("User not found with the given username.");
		}
		return existingUser;
	}

	@Override
	public void resetPassword(ResetPasswordRequest resetPasswordRequest) throws ValidationException {
		User existingUser = userRepository.findByUserName(resetPasswordRequest.getUserName());
		if(existingUser != null) {
			if(existingUser.getPassword().equals(resetPasswordRequest.getOldPassword())) {
				existingUser.setPassword(resetPasswordRequest.getNewPassword());
				existingUser = userRepository.save(existingUser);
			}else {
				throw new ValidationException("Password mismatch, please check and retry.");
			}
		}
	}	
}