package com.upday.service.impl;

import com.upday.entity.Article;
import com.upday.exception.ArticleNotFoundException;
import com.upday.repository.ArticleRepository;
import com.upday.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Amit Mohapatra on 3/17/18.
 */
@Service
public class DefaultArticleService implements ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	@Override
	public Article findArticleById(final Long articleId) throws ArticleNotFoundException {
		return findArticle(articleId);
	}

	@Override
	public Iterable<Article> findAllArticles() {
		return articleRepository.findAll();
	}

	@Override
	public Article create(final Article article) throws ArticleNotFoundException {
		return articleRepository.save(article);
	}

	@Override
	@Transactional
	public void update(final Article article) throws ArticleNotFoundException {
		Article updateArticle = findArticle(article.getId());
		updateArticle.setHeader(article.getHeader());
		updateArticle.setDescription(article.getDescription());
		updateArticle.setText(article.getText());
	}

	@Override
	@Transactional
	public void delete(final Long carId) throws ArticleNotFoundException {
		Article article = findArticle(carId);
		article.setDeleted(Boolean.TRUE);
	}

	private Article findArticle(final Long articleId) throws ArticleNotFoundException {
		Article article = articleRepository.findOne(articleId);
		if (null == article) {
			throw new ArticleNotFoundException("Could not find article with id: " + articleId);
		}
		return article;
	}

}
