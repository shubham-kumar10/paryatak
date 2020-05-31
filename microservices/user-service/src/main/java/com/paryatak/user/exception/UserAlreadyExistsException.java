package com.paryatak.user.exception;

public class UserAlreadyExistsException extends Exception {

	private static final long serialVersionUID = -767930952639222090L;

	public UserAlreadyExistsException() {
		// TODO Auto-generated constructor stub
		super("User already exists");
	}

}
