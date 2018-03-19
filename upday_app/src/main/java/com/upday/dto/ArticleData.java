package com.upday.dto;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ArticleData {

	private Long id;
	@JsonIgnore
	private String createdByUsername;
	private String header;
	private String description;
	private String text;
	@NotNull
	private List<String> authors;
	@NotNull
	private Set<String> tags;
	@JsonIgnore
	private String createdDate;
	@JsonIgnore
	private String updatedDate;

	private ArticleData() {
	}

	private ArticleData(ArticleDataBuilder obj) {
		this.id = obj.id;
		this.createdByUsername = obj.createdByUsername;
		this.header = obj.header;
		this.description = obj.description;
		this.text = obj.text;
		this.authors = obj.authors;
		this.tags = obj.tags;
		this.createdDate = obj.createdDate;
		this.updatedDate = obj.updatedDate;
	}

	public Long getId() {
		return id;
	}

	@JsonProperty
	public String getCreatedByUsername() {
		return createdByUsername;
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

	public List<String> getAuthors() {
		return authors;
	}

	public Set<String> getTags() {
		return tags;
	}

	@JsonProperty
	public String getCreatedDate() {
		return createdDate;
	}

	@JsonProperty
	public String getUpdatedDate() {
		return updatedDate;
	}

	public static ArticleDataBuilder newArticleDataBuilder() {
		return new ArticleDataBuilder();
	}

	public static class ArticleDataBuilder {
		private Long id;
		private String createdByUsername;
		private String header;
		private String description;
		private String text;
		private List<String> authors;
		private Set<String> tags;
		private String createdDate;
		private String updatedDate;

		public ArticleDataBuilder setId(Long id) {
			this.id = id;
			return this;
		}

		public ArticleDataBuilder setCreatedByUsername(String username) {
			this.createdByUsername = username;
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

		public ArticleDataBuilder setAuthors(List<String> authors) {
			this.authors = authors;
			return this;
		}

		public ArticleDataBuilder setTags(Set<String> tags) {
			this.tags = tags;
			return this;
		}

		public ArticleDataBuilder setCreatedDate(String createdDate) {
			this.createdDate = createdDate;
			return this;
		}

		public ArticleDataBuilder setUpdatedDate(String updatedDate) {
			this.updatedDate = updatedDate;
			return this;
		}

		public ArticleData build() {
			return new ArticleData(this);
		}

	}

}
