package com.upday.service.impl;

import java.time.ZonedDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upday.entity.Article;
import com.upday.exception.ConstraintsViolationException;
import com.upday.exception.EntityNotFoundException;
import com.upday.repository.ArticleRepository;
import com.upday.service.ArticleService;

/**
 * Created by Amit Mohapatra on 03/17/18.
 */
@Service
public class DefaultArticleService implements ArticleService {

	static Logger logger = Logger.getLogger(DefaultArticleService.class.getName());

	@Autowired
	private ArticleRepository articleRepository;

	@Override
	public Article findArticleById(final Long articleId) throws EntityNotFoundException {
		return findArticle(articleId);
	}

	@Override
	public Iterable<Article> findAllArticles() {
		return articleRepository.findAllArticles();
	}

	@Override
	public Article create(final Article source) throws ConstraintsViolationException {
		Article article;
		try {
			article = articleRepository.save(source);
		} catch (DataIntegrityViolationException e) {
			logger.log(Level.SEVERE, "Some constraints are thrown due to driver creation", e);
			throw new ConstraintsViolationException(e.getMessage());
		}
		return article;
	}

	@Override
	@Transactional
	public void update(final Article article) throws EntityNotFoundException {
		Article updateArticle = findArticle(article.getId());
		updateArticle.setDateUpdated(ZonedDateTime.now());
		updateArticle.setHeader(article.getHeader());
		updateArticle.setDescription(article.getDescription());
		updateArticle.setText(article.getText());
		updateArticle.setAuthors(article.getAuthors());
		updateArticle.setTags(article.getTags());
		articleRepository.save(updateArticle);
	}

	@Override
	@Transactional
	public void delete(final Long articleId) throws EntityNotFoundException {
		Article article = findArticle(articleId);
		article.setDeleted(Boolean.TRUE);
		articleRepository.save(article);
	}

	private Article findArticle(final Long articleId) throws EntityNotFoundException {
		Article article = articleRepository.findOne(articleId);
		if (null == article || article.getDeleted() == true) {
			throw new EntityNotFoundException("Could not find article with id: " + articleId);
		}
		return article;
	}

	@Override
	public Iterable<Article> findArticlesByDate(ZonedDateTime begindate, ZonedDateTime enddate)
			throws EntityNotFoundException {
		return articleRepository.findArticlesByDate(begindate, enddate);
	}

}
