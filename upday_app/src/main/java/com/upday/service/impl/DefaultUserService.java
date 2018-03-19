package com.upday.service.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.upday.entity.ApplicationUser;
import com.upday.exception.ConstraintsViolationException;
import com.upday.repository.UserRepository;
import com.upday.service.UserService;

/**
 * Created by Amit Mohapatra on 03/17/18.
 */
@Service
public class DefaultUserService implements UserService {

	static Logger logger = Logger.getLogger(DefaultArticleService.class.getName());

	@Autowired
	private UserRepository userRepository;

	@Override
	public ApplicationUser create(ApplicationUser source) throws ConstraintsViolationException {
		ApplicationUser user;
		try {
			user = userRepository.save(source);
		} catch (DataIntegrityViolationException e) {
			logger.log(Level.SEVERE, "Some constraints are thrown due to driver creation", e);
			throw new ConstraintsViolationException(e.getMessage());
		}
		return user;
	}

	@Override
	public ApplicationUser findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
