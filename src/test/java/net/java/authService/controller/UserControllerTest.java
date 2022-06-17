package net.java.authService.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import net.java.authService.BaseTest;
import net.java.authService.Exception.UserAlreadyExistsException;
import net.java.authService.domain.ResetPasswordRequest;
import net.java.authService.domain.User;
import net.java.authService.service.UserService;

public class UserControllerTest extends BaseTest {

	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	protected MockMvc mvc;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	private static final String URI = "/auth/";

	@Test
	public void testFetchUserByUsername() throws Exception {
		Map<String, User> map = new HashMap<String, User>();
		User user = new User();
		user.setId(1);
		user.setFirstName("First Name");
		user.setLastName("Last Name");
		user.setEmail("email");
		user.setUserName("username");
		user.setPassword("password");
		map.put("user", user);
		Mockito.when(userService.fetchUserByUsername(anyString())).thenReturn(user);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI + "fetch/username")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		String expectedJson = this.mapToJson(map);
		String outputInJson = mvcResult.getResponse().getContentAsString();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(expectedJson, outputInJson);
	}
	
	@Test
	public void testUserRegister() throws Exception {
		User request = new User();
		request.setId(1);
		request.setFirstName("First Name");
		request.setLastName("Last Name");
		request.setEmail("email");
		request.setUserName("username");
		request.setPassword("password");
		String inputInJson = this.mapToJson(request);
		Mockito.when(userService.register(any(User.class))).thenReturn(request);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI + "register").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		assertEquals(inputInJson, mvcResult.getResponse().getContentAsString());
	}
	
	@Test
	public void testUserUpdate() throws Exception {
		Map<String, User> map = new HashMap<String, User>();
		User request = new User();
		request.setId(1);
		request.setFirstName("First Name");
		request.setLastName("Last Name");
		request.setEmail("email");
		request.setUserName("username");
		request.setPassword("password");
		map.put("user", request);
		String inputInJson = this.mapToJson(request);
		String expectedResponse = this.mapToJson(map);
		Mockito.when(userService.updateUser(any(User.class))).thenReturn(request);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(URI + "update").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(expectedResponse, mvcResult.getResponse().getContentAsString());
	}
	
	@Test
	public void testUserLogin() throws Exception {
		User request = new User();
		request.setId(1);
		request.setFirstName("First Name");
		request.setLastName("Last Name");
		request.setEmail("email");
		request.setUserName("username");
		request.setPassword("password");
		String inputInJson = this.mapToJson(request);
		Mockito.when(userService.login(anyString(), anyString())).thenReturn(request);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI + "login").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
}
