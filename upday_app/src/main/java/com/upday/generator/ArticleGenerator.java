package com.upday.generator;

import com.upday.dto.ArticleData;
import com.upday.entity.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amit Mohapatra on 03/17/18.
 */
public class ArticleGenerator {

	public static ArticleData generate(Article source) {
		return ArticleData.newArticleDataBuilder().setId(source.getId()).setHeader(source.getHeader())
				.setDescription(source.getDescription()).setText(source.getText()).build();
	}

	public static List<ArticleData> generate(Iterable<Article> source) {
		List<ArticleData> articleDataList = new ArrayList<>();
		source.forEach(article -> articleDataList.add(generate(article)));
		return articleDataList;
	}

	public static Article convert(ArticleData source) {
		Article article = new Article();
		article.setHeader(source.getHeader());
		article.setDescription(source.getDescription());
		article.setText(source.getText());
		return article;

	}

}
