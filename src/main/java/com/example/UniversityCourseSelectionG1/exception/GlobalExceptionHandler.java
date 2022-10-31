package com.example.UniversityCourseSelectionG1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value=NotFoundException.class)
	public ResponseEntity<String> handleNotFoundException(NotFoundException e)
	{
		return new ResponseEntity<String>("Not found", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=NotLoggedInException.class)
	public ResponseEntity<String> handleNotLoggedInException(NotLoggedInException e)
	{
		return new ResponseEntity<String>("Not logged in", HttpStatus.FORBIDDEN);
	}
}
