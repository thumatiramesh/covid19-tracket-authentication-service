package net.java.authService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT , reason ="user already exists")
public class UserAlreadyExistsException extends Exception {



}
