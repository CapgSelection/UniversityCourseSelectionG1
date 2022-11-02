package com.example.UniversityCourseSelectionG1.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value=NotFoundException.class)
	public ResponseEntity<ExceptionDescription> handleNotFoundException(NotFoundException e)
	{
		ExceptionDescription desc=new ExceptionDescription(e.getMessage());
		return new ResponseEntity<ExceptionDescription>(desc, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=NotLoggedInException.class)
	public ResponseEntity<ExceptionDescription> handleNotLoggedInException(NotLoggedInException e)
	{
		ExceptionDescription desc=new ExceptionDescription(e.getMessage());
		return new ResponseEntity<ExceptionDescription>(desc, HttpStatus.FORBIDDEN);
	}
	
}
