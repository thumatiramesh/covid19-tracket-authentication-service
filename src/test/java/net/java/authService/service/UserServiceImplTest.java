package net.java.authService.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import net.java.authService.Exception.UserAlreadyExistsException;
import net.java.authService.Exception.UserNotFoundException;
import net.java.authService.Exception.ValidationException;
import net.java.authService.domain.ResetPasswordRequest;
import net.java.authService.domain.User;

public class UserServiceImplTest {

	@Test
	public void testRegister() throws UserAlreadyExistsException, UserNotFoundException {
		UserServiceImpl service = mock(UserServiceImpl.class);
		when(service.register(any()))
		.thenReturn(new User(1, "1@gmail.com", "1pass", "1username", "1firstName", "1lastName"));
		User dummy = service.register(any());
		assertNotNull(dummy);
	}
	
	@Test(expected = UserAlreadyExistsException.class)
	public void testRegisterWithException() throws UserAlreadyExistsException, UserNotFoundException {
		UserServiceImpl service = mock(UserServiceImpl.class);
		when(service.register(any())).thenThrow(new UserAlreadyExistsException());
		service.register(any());
	}
	
	@Test
	public void testFetchByUserName() throws UserNotFoundException {
		UserServiceImpl service = mock(UserServiceImpl.class);
		when(service.fetchUserByUsername(anyString()))
				.thenReturn(new User(1, "1@gmail.com", "1pass", "1username", "1firstName", "1lastName"));
		User dummy = service.fetchUserByUsername("anystring");
		assertNotNull(dummy);
		assertEquals(dummy.getId(), 1);
		assertEquals(dummy.getEmail(), "1@gmail.com");
	}
	
	@Test
	public void testLogin() throws UserNotFoundException {
		UserServiceImpl service = mock(UserServiceImpl.class);
		when(service.login(anyString(), anyString()))
				.thenReturn(new User(1, "1@gmail.com", "dummypassword", "dummyusername", "1firstName", "1lastName"));
		User dummy = service.login("dummyusername", "dummypassword");
		assertNotNull(dummy);
		assertEquals(dummy.getUserName(), "dummyusername");
		assertEquals(dummy.getPassword(), "dummypassword");
	}
	
	@Test(expected = UserNotFoundException.class)
	public void testLoginWithException() throws UserNotFoundException {
		UserServiceImpl service = mock(UserServiceImpl.class);
		when(service.login(anyString(), anyString()))
				.thenThrow(new UserNotFoundException());
		service.login("username", "password");
	}
	
	@Test
	public void testUpdateUser() throws UserNotFoundException, ValidationException {
		UserServiceImpl service = mock(UserServiceImpl.class);
		when(service.updateUser(any()))
				.thenReturn(new User(1, "1@gmail.com", "dummypassword", "dummyusername", "1firstName", "1lastName"));
		User dummy = service.updateUser(any());
		assertNotNull(dummy);
		assertEquals(dummy.getUserName(), "dummyusername");
		assertEquals(dummy.getPassword(), "dummypassword");
	}
	
	@Test(expected = ValidationException.class)
	public void testUpdateUserWithException() throws UserNotFoundException, ValidationException {
		UserServiceImpl service = mock(UserServiceImpl.class);
		when(service.updateUser(any())).thenThrow(new ValidationException("validation failure"));
		service.updateUser(any());
	}
	
}
