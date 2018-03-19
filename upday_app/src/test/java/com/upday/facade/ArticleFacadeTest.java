package com.upday.facade;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
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
import com.upday.dto.ArticleData;
import com.upday.entity.ApplicationUser;
import com.upday.entity.Article;
import com.upday.exception.EntityNotFoundException;
import com.upday.facade.impl.DefaultArticleFacade;
import com.upday.service.ArticleService;
import com.upday.service.UserService;

public class ArticleFacadeTest extends HelperTest {

	@Mock
	private UserService userService;

	@Mock
	private ArticleService articleService;

	@InjectMocks
	private DefaultArticleFacade articleFacade;

	@BeforeClass
	public static void setUp() {
		MockitoAnnotations.initMocks(DefaultArticleFacade.class);
	}

	@Test
	public void testFindArticleById() {
		Article article = getArticle();
		when(articleService.findArticleById(any(Long.class))).thenReturn(article);
		articleFacade.findArticleById(any(Long.class));
		verify(articleService, times(1)).findArticleById(any(Long.class));
	}

	@Test
	public void testFindAllArticles() {
		Iterable<Article> articles = Collections.singletonList(getArticle());
		when(articleService.findAllArticles()).thenReturn(articles);
		articleFacade.findAllArticles();
		verify(articleService, times(1)).findAllArticles();
	}

	@Test
	public void testCreate() {
		Article article = getArticle();
		ApplicationUser user = getApplicationUser();
		ArticleData articleData = getArticleData();
		when(articleService.create(any(Article.class))).thenReturn(article);
		when(userService.findByUsername(any(String.class))).thenReturn(user);
		articleFacade.create(articleData);
		verify(articleService, times(1)).create(any(Article.class));
	}

	@Test
	public void testUpdate() throws EntityNotFoundException {
		ArticleData carData = getArticleData();
		ApplicationUser user = getApplicationUser();
		doNothing().when(articleService).update(any(Article.class));
		when(userService.findByUsername(any(String.class))).thenReturn(user);
		articleFacade.update(carData);
		verify(articleService, times(1)).update(any(Article.class));
	}

	@Test
	public void testDelete() throws EntityNotFoundException {
		doNothing().when(articleService).delete(any(Long.class));
		articleFacade.delete(any(Long.class));
		verify(articleService, times(1)).delete(any(Long.class));
	}

}
