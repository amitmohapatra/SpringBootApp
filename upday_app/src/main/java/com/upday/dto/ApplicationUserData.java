package com.upday.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApplicationUserData {

	@JsonIgnore
	private Long id;
	@NotNull
	private String userName;
	@NotNull
	private String password;
	@NotNull
	private String name;

	private ApplicationUserData() {
	}

	private ApplicationUserData(ApplicationUserDataBuilder buildObj) {
		this.id = buildObj.id;
		this.userName = buildObj.userName;
		this.password = buildObj.password;
		this.name = buildObj.name;
	}

	@JsonProperty
	public Long getId() {
		return id;
	}
	
	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public static ApplicationUserDataBuilder newApplicationUserDataBuilder() {
		return new ApplicationUserDataBuilder();
	}

	public static class ApplicationUserDataBuilder {
		private Long id;
		private String userName;
		private String password;
		private String name;
		
		public ApplicationUserDataBuilder setId(Long id) {
			this.id = id;
			return this;
		}

		public ApplicationUserDataBuilder setUserName(String userName) {
			this.userName = userName;
			return this;
		}

		public ApplicationUserDataBuilder setPassword(String password) {
			this.password = password;
			return this;
		}

		public ApplicationUserDataBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public ApplicationUserData build() {
			return new ApplicationUserData(this);
		}

	}

}
