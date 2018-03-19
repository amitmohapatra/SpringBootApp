package com.upday.facade;

import java.util.List;

import com.upday.dto.ArticleData;
import com.upday.dto.ArticleDateData;
import com.upday.exception.ConstraintsViolationException;
import com.upday.exception.EntityNotFoundException;

/**
 * Created by Amit Mohapatra on 3/17/18.
 */
public interface ArticleFacade {

	ArticleData findArticleById(final Long articleId) throws EntityNotFoundException;

	List<ArticleData> findAllArticles();

	ArticleData create(final ArticleData articlerData) throws ConstraintsViolationException;

	void update(final ArticleData articleData) throws EntityNotFoundException;

	void delete(final Long articleId) throws EntityNotFoundException;

	List<ArticleData> findArticlesByDate(ArticleDateData articleDateData) throws EntityNotFoundException;
}
