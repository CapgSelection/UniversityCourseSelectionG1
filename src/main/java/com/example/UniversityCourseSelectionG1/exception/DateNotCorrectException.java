package com.example.UniversityCourseSelectionG1.exception;

public class DateNotCorrectException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public DateNotCorrectException()
	{
		super();
	}
	public DateNotCorrectException(String message)
	{
		super(message);
	}
}
