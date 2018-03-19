package com.upday.service;

import com.upday.entity.ApplicationUser;
import com.upday.exception.ConstraintsViolationException;

/**
 * Created by Amit Mohapatra on 03/17/18.
 */
public interface UserService {

	ApplicationUser create(final ApplicationUser user) throws ConstraintsViolationException;
	
	ApplicationUser findByUsername(String username);
}
