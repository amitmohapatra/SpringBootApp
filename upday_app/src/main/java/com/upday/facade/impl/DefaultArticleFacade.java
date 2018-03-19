package com.upday.facade.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.upday.dto.ArticleData;
import com.upday.dto.ArticleDateData;
import com.upday.entity.ApplicationUser;
import com.upday.entity.Article;
import com.upday.exception.ConstraintsViolationException;
import com.upday.exception.EntityNotFoundException;
import com.upday.facade.ArticleFacade;
import com.upday.generator.ArticleGenerator;
import com.upday.service.ArticleService;
import com.upday.service.UserService;

@Service
public class DefaultArticleFacade implements ArticleFacade {

	@Autowired
	private ArticleService articleService;

	@Autowired
	private UserService userService;

	@Override
	public ArticleData findArticleById(Long articleId) throws EntityNotFoundException {
		return ArticleGenerator.generate(articleService.findArticleById(articleId));
	}

	@Override
	public List<ArticleData> findAllArticles() {
		return ArticleGenerator.generate(articleService.findAllArticles());
	}

	@Override
	public ArticleData create(ArticleData articleData) throws ConstraintsViolationException {
		List<ApplicationUser> authors = articleData.getAuthors().parallelStream()
				.map(author -> validateUserByUsername(author)).collect(Collectors.toList());
		Article article = ArticleGenerator.convert(articleData, authors);
		article.setCreated_by(SecurityContextHolder.getContext().getAuthentication().getName());
		return ArticleGenerator.generate(articleService.create(article));
	}

	private ApplicationUser validateUserByUsername(String user) {
		ApplicationUser checkUser = userService.findByUsername(user);
		if (checkUser == null) {
			throw new ConstraintsViolationException("user does not exist with username : " + user);
		}
		return checkUser;
	}

	@Override
	public void update(final ArticleData articleData) throws EntityNotFoundException {
		List<ApplicationUser> authors = new HashSet<String>(articleData.getAuthors()).parallelStream()
				.map(author -> validateUserByUsername(author)).collect(Collectors.toList());
		Article article = ArticleGenerator.convert(articleData, authors);
		article.setId(articleData.getId());
		article.setDateUpdated(ZonedDateTime.now());
		article.setCreated_by(SecurityContextHolder.getContext().getAuthentication().getName());
		articleService.update(article);
	}

	@Override
	public void delete(Long articleId) throws EntityNotFoundException {
		articleService.delete(articleId);
	}

	@Override
	public List<ArticleData> findArticlesByDate(ArticleDateData articleDateData) throws EntityNotFoundException {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		ZoneId timeZone = ZoneId.systemDefault();
		ZonedDateTime beginDateTime = LocalDateTime.parse(articleDateData.getBegindate(), formatter).atZone(timeZone);
		ZonedDateTime endDateTime = LocalDateTime.parse(articleDateData.getEnddate(), formatter).atZone(timeZone);

		if (endDateTime.compareTo(beginDateTime) < 0) {
			throw new EntityNotFoundException("Begin date should not be greater than start date");
		}
		return ArticleGenerator.generate(articleService.findArticlesByDate(beginDateTime, endDateTime));
	}

}
