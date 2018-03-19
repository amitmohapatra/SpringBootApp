package com.upday;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.upday.config.CustomUserDetails;
import com.upday.entity.ApplicationUser;
import com.upday.entity.Role;
import com.upday.repository.UserRepository;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class Application {

	public static void main(String... args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repo) throws Exception {
		builder.userDetailsService(new UserDetailsService() {

			@Override
			public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
				if (repo.count() == 0)
					repo.save(new ApplicationUser("admin", "admin", "admin", Arrays.asList(new Role("ADMIN"))));
				return new CustomUserDetails(repo.findByUsername(s));
			}
		});
	}
}
