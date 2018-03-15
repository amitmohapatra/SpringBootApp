package com.upday.facade.impl;

import com.upday.dto.ArticleData;
import com.upday.entity.Article;
import com.upday.exception.ArticleNotFoundException;
import com.upday.facade.ArticleFacade;
import com.upday.generator.ArticleGenerator;
import com.upday.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultArticleFacade implements ArticleFacade {

	@Autowired
	private ArticleService articleService;

	@Override
	public ArticleData findArticleById(Long articleId) throws ArticleNotFoundException {
		return ArticleGenerator.generate(articleService.findArticleById(articleId));
	}

	@Override
	public List<ArticleData> findAllArticles() {
		return ArticleGenerator.generate(articleService.findAllArticles());
	}

	@Override
	public ArticleData create(ArticleData articleData) throws ArticleNotFoundException {
		Article article = ArticleGenerator.convert(articleData);
		return ArticleGenerator.generate(articleService.create(article));
	}

	@Override
	public void update(final ArticleData articleData) throws ArticleNotFoundException {
		Article article = ArticleGenerator.convert(articleData);
		article.setId(article.getId());
		articleService.update(article);
	}

	@Override
	public void delete(Long articleId) throws ArticleNotFoundException {
		articleService.delete(articleId);
	}
}
