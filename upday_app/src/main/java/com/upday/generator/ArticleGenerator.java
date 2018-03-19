package com.upday.generator;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.upday.dto.ArticleData;
import com.upday.entity.ApplicationUser;
import com.upday.entity.Article;

/**
 * Created by Amit Mohapatra on 03/17/18.
 */
public class ArticleGenerator {

	public static ArticleData generate(Article source) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
		return ArticleData.newArticleDataBuilder().setId(source.getId()).setHeader(source.getHeader())
				.setCreatedByUsername(source.getCreated_by())
				.setDescription(source.getDescription()).setText(source.getText())
				.setAuthors(
						source.getAuthors().stream().map(author -> author.getUsername()).collect(Collectors.toList()))
				.setTags(source.getTags())
				.setCreatedDate(formatter.format(source.getDateCreated()))
				.setUpdatedDate(formatter.format(source.getDateUpdated())).build();
	}

	public static List<ArticleData> generate(Iterable<Article> source) {
		List<ArticleData> articleDataList = new ArrayList<>();
		source.forEach(article -> articleDataList.add(generate(article)));
		return articleDataList;
	}

	public static Article convert(ArticleData source, List<ApplicationUser> users) {
		Article article = new Article();
		article.setHeader(source.getHeader());
		article.setDescription(source.getDescription());
		article.setText(source.getText());
		article.getAuthors().addAll(users);
		article.getTags().addAll(source.getTags().stream().map(String::toLowerCase).collect(Collectors.toSet()));
		return article;

	}

}
