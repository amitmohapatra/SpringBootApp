package com.upday.generator;

import com.upday.dto.ApplicationUserData;
import com.upday.entity.ApplicationUser;

public class UserGenerator {

	public static ApplicationUserData generate(ApplicationUser source) {
		return ApplicationUserData.newApplicationUserDataBuilder().setName(source.getName())
				.setUserName(source.getUsername()).build();
	}

	public static ApplicationUser convert(ApplicationUserData source) {
		ApplicationUser user = new ApplicationUser(source.getUserName(), source.getPassword(), source.getName());
		return user;

	}
}
