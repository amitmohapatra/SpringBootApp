package com.upday.dto;

import javax.validation.constraints.NotNull;

public class ArticleDateData {

	@NotNull
	private String begindate;

	@NotNull
	private String enddate;

	public static ArticleDateDataBuilder newArticleDateDataBuilder() {
		return new ArticleDateDataBuilder();
	}

	private ArticleDateData() {
	}

	private ArticleDateData(ArticleDateDataBuilder obj) {
		this.begindate = obj.begindate;
		this.begindate = obj.enddate;
	}

	public String getBegindate() {
		return begindate;
	}

	public String getEnddate() {
		return enddate;
	}

	public static class ArticleDateDataBuilder {
		private String begindate;
		private String enddate;

		public ArticleDateDataBuilder setBegindate(String begindate) {
			this.begindate = begindate;
			return this;
		}

		public ArticleDateDataBuilder setEnddate(String enddate) {
			this.enddate = enddate;
			return this;
		}

		public ArticleDateData build() {
			return new ArticleDateData(this);
		}
	}
}
