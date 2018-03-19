package com.upday.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * This class is responsible for Oauth2 resource configuration. 
 * Created by Amit Mohapatra on 17/03/18.
 */
@Configuration
@EnableResourceServer
public class UpdayOAuth2ResourceConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/v1/**").authenticated();
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}

}
