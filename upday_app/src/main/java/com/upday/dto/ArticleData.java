package com.upday.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleData {

	@JsonIgnore
	private Long id;
	private String header;
	private String description;
	private String text;

	private ArticleData() {
	}

	private ArticleData(Long id, String header, String description, String text) {
		this.id = id;
		this.header = header;
		this.description = description;
		this.text = text;
	}

	@JsonProperty
	public Long getId() {
		return id;
	}

	public String getHeader() {
		return header;
	}

	public String getDescription() {
		return description;
	}

	public String getText() {
		return text;
	}

	public static ArticleDataBuilder newArticleDataBuilder() {
		return new ArticleDataBuilder();
	}

	public static class ArticleDataBuilder {
		private Long id;
		private String header;
		private String description;
		private String text;

		public ArticleDataBuilder setId(Long id) {
			this.id = id;
			return this;
		}

		public ArticleDataBuilder setHeader(String header) {
			this.header = header;
			return this;
		}

		public ArticleDataBuilder setDescription(String description) {
			this.description = description;
			return this;
		}

		public ArticleDataBuilder setText(String text) {
			this.text = text;
			return this;
		}

		public ArticleData build() {
			return new ArticleData(id, header, description, text);
		}

	}

}
