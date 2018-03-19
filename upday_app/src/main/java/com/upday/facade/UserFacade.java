package com.upday.facade;

import com.upday.dto.ApplicationUserData;
import com.upday.exception.ConstraintsViolationException;

/**
* Created by Amit Mohapatra on 3/17/18.
*/
public interface UserFacade {

	ApplicationUserData create(final ApplicationUserData userData) throws ConstraintsViolationException;
}