package com.upday.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ArticleAuthorData {
	
	@NotNull
	private String username;

	public String getUsername() {
		return username;
	}
	
	public static ArticleAuthorDataBuilder newArticleAuthorDataBuilder() {
		return new ArticleAuthorDataBuilder();
	}

	private ArticleAuthorData() {
	}

	private ArticleAuthorData(ArticleAuthorDataBuilder obj) {
		this.username = obj.username;
	}
	
	public static class ArticleAuthorDataBuilder {
		private String username;
		
		public ArticleAuthorDataBuilder setUsername(String username) {
			this.username = username;
			return this;
		}
		
		public ArticleAuthorData build() {
			return new ArticleAuthorData(this);
		}
	}
}
