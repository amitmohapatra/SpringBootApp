package com.upday.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upday.dto.ApplicationUserData;
import com.upday.entity.ApplicationUser;
import com.upday.exception.ConstraintsViolationException;
import com.upday.facade.UserFacade;
import com.upday.generator.UserGenerator;
import com.upday.service.UserService;

@Service
public class DefaultUserFacade implements UserFacade {

	@Autowired
	private UserService userService;

	@Override
	public ApplicationUserData create(ApplicationUserData userData) throws ConstraintsViolationException {
		if (userData.getName() == null) {
			throw new ConstraintsViolationException("User name can not be empty");
		} else if (userData.getPassword() == null) {
			throw new ConstraintsViolationException("User password can not be empty");
		} else if (userData.getUserName() == null) {
			throw new ConstraintsViolationException("User username can not be empty");
		} else {
			if (userService.findByUsername(userData.getUserName()) == null) {
				ApplicationUser user = UserGenerator.convert(userData);
				return UserGenerator.generate(userService.create(user));
			} else {
				throw new ConstraintsViolationException("User exist with username : " + userData.getUserName());
			}
		}
	}

}
