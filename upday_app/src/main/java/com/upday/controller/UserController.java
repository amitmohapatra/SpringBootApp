package com.upday.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.upday.dto.ApplicationUserData;
import com.upday.exception.ConstraintsViolationException;
import com.upday.facade.UserFacade;

/**
 * All operations with a user will be routed by this controller. 
 * Created by Amit Mohapatra on 3/17/18.
 */
@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	private UserFacade userFacade;

	@RequestMapping(value = "/sign-up", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ApplicationUserData> createUser(@Valid @RequestBody ApplicationUserData userData)
			throws ConstraintsViolationException {
		return new ResponseEntity<>(userFacade.create(userData), HttpStatus.CREATED);
	}

}
