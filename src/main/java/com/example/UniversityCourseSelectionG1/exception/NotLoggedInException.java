package com.example.UniversityCourseSelectionG1.exception;

public class NotLoggedInException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public NotLoggedInException()
	{
		super();
	}
	public NotLoggedInException(String message)
	{
		super(message);
	}
}
