package com.upday.repository;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.upday.entity.Article;

public class ArticleRepositoryTest extends HelperRepositoryTest {

	@Autowired
	private ArticleRepository articleRepository;

	@Test
	public void testDriverById() {
		Article article = articleRepository.findOne(1L);
		Assert.assertNotNull(article);
	}

	@Test
	public void testAllArticles() {
		List<Article> articles = Lists.newArrayList(articleRepository.findAll());
		Assert.assertThat(articles, hasSize(3));
	}

}
