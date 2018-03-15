package com.upday.facade;

import com.upday.dto.ArticleData;
import com.upday.exception.ArticleNotFoundException;

import java.util.List;

/**
 * Created by Amit Mohapatra on 3/17/19.
 */
public interface ArticleFacade {

	ArticleData findArticleById(final Long articleId) throws ArticleNotFoundException;

	List<ArticleData> findAllArticles();

	ArticleData create(final ArticleData articlerData) throws ArticleNotFoundException;

	void update(final ArticleData articleData) throws ArticleNotFoundException;

	void delete(final Long articleId) throws ArticleNotFoundException;
}
