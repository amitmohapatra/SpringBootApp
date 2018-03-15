package com.upday.service;

import com.upday.entity.Article;
import com.upday.exception.ArticleNotFoundException;

/**
 * Created by Amit Mohapatra on 3/17/18.
 */
public interface ArticleService {

	Article findArticleById(final Long articleId) throws ArticleNotFoundException;

	Iterable<Article> findAllArticles();

	Article create(final Article article) throws ArticleNotFoundException;

	void update(final Article article) throws ArticleNotFoundException;

	void delete(final Long articleId) throws ArticleNotFoundException;

}
