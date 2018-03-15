package com.upday.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Amit Mohapatra on 3/17/19.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ArticleNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public ArticleNotFoundException(String message) {
		super(message);
	}

}
