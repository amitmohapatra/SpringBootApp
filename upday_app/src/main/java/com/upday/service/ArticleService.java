package com.upday.service;

import java.time.ZonedDateTime;

import com.upday.entity.Article;
import com.upday.exception.ConstraintsViolationException;
import com.upday.exception.EntityNotFoundException;

/**
 * Created by Amit Mohapatra on 03/17/18.
 */
public interface ArticleService {

	Article findArticleById(final Long articleId) throws EntityNotFoundException;

	Iterable<Article> findAllArticles();

	Article create(final Article article) throws ConstraintsViolationException;

	void update(final Article article) throws EntityNotFoundException;

	void delete(final Long articleId) throws EntityNotFoundException;

	Iterable<Article> findArticlesByDate(ZonedDateTime begindate, ZonedDateTime enddate) throws EntityNotFoundException;
}
