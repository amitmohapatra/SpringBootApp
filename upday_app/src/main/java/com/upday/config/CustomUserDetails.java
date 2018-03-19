package com.upday.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.upday.entity.ApplicationUser;
import com.upday.entity.Role;

public class CustomUserDetails implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	Collection<? extends GrantedAuthority> authorties;
	
	public CustomUserDetails(ApplicationUser userByName) {
		this.username = userByName.getUsername();
		this.password = userByName.getPassword();
		
		List<GrantedAuthority> auths = new ArrayList<>();
		for(Role role:userByName.getRoles()) {
			auths.add(new SimpleGrantedAuthority(role.getName().toUpperCase()));
		}
		authorties = auths;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorties;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
