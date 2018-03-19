package com.upday.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.upday.HelperTest;
import com.upday.entity.Article;
import com.upday.repository.ArticleRepository;
import com.upday.service.impl.DefaultArticleService;

public class ArticleServiceTest extends HelperTest {

	@Mock
	private ArticleRepository articleRepository;

	@InjectMocks
	private DefaultArticleService articleService;

	@BeforeClass
	public static void setUp() {
		MockitoAnnotations.initMocks(DefaultArticleService.class);
	}

	@Test
	public void testFindArticleById() {
		Article article = getArticle();
		when(articleRepository.findOne(any(Long.class))).thenReturn(article);
		articleService.findArticleById(any(Long.class));
		verify(articleRepository, times(1)).findOne(any(Long.class));
	}

	@Test
	public void testFindAllArticles() {
		Iterable<Article> articles = Collections.singletonList(getArticle());
		when(articleRepository.findAll()).thenReturn(articles);
		articleService.findAllArticles();
		verify(articleRepository, times(1)).findAllArticles();
	}

	@Test
	public void testCreate() {
		Article Article = getArticle();
		when(articleRepository.save(any(Article.class))).thenReturn(Article);
		articleService.create(Article);
		verify(articleRepository, times(1)).save(Article);
	}

	@Test
	public void testUpdate() throws Exception {
		Article Article = getArticle();
		when(articleRepository.findOne(any(Long.class))).thenReturn(Article);
		articleService.update(Article);
		verify(articleRepository, times(1)).findOne(any(Long.class));
	}

	@Test
	public void testDelete() {
		Article Article = getArticle();
		when(articleRepository.findOne(any(Long.class))).thenReturn(Article);
		articleService.delete(1L);
		verify(articleRepository, times(1)).findOne(any(Long.class));
	}
}
