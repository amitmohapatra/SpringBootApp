package com.upday.repository;

import org.springframework.data.repository.CrudRepository;

import com.upday.entity.ApplicationUser;

public interface UserRepository extends CrudRepository<ApplicationUser, Long> {
	ApplicationUser findByUsername(String username);
}
