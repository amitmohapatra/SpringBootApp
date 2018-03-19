package com.upday.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upday.HelperTest;
import com.upday.dto.ArticleData;
import com.upday.facade.ArticleFacade;

public class ArticleControllerTest extends HelperTest {

	private static final ObjectMapper mapper = new ObjectMapper();

	private MockMvc mvc;

	@Mock
	private ArticleFacade articleFacade;

	@InjectMocks
	private ArticleController articleController;

	@BeforeClass
	public static void setUp() {
		MockitoAnnotations.initMocks(ArticleController.class);
	}

	@Before
	public void init() {
		mvc = MockMvcBuilders.standaloneSetup(articleController).dispatchOptions(true).build();
	}

	@Test
	public void testGetOneArticle() throws Exception {
		ArticleData articleData = getArticleData();
		doReturn(articleData).when(articleFacade).findArticleById(any(Long.class));
		articleController.getArticle(1L);
		MvcResult result = mvc.perform(get("/v1/article/{articleId}", 1))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		final String responseBody = result.getResponse().getContentAsString();
		Assert.assertTrue(responseBody.contains("myusername"));
	}

	@Test
	public void getAllArticless() throws Exception {
		List<ArticleData> articles = Collections.singletonList(getArticleData());
		doReturn(articles).when(articleFacade).findAllArticles();
		articleController.getAllArticles();
		MvcResult result = mvc.perform(get("/v1/article")).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		final String responseBody = result.getResponse().getContentAsString();
		Assert.assertTrue(responseBody.contains("myusername"));
	}

	@Test
	public void createArticle() throws Exception {
		ArticleData articleData = getArticleData();
		String jsonInString = mapper.writeValueAsString(articleData);
		doReturn(articleData).when(articleFacade).create(any(ArticleData.class));
		articleController.createArticle(articleData);
		MvcResult result = mvc
				.perform(post("/v1/article").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(jsonInString))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
		final String responseBody = result.getResponse().getContentAsString();
		Assert.assertTrue(responseBody.contains("myusername"));
	}

	@Test
	public void updateArticle() throws Exception {
		ArticleData articleData = getArticleData();
		String jsonInString = mapper.writeValueAsString(articleData);
		doNothing().when(articleFacade).update(any(ArticleData.class));
		articleController.updateArticle(articleData);
		MvcResult result = mvc
				.perform(put("/v1/article").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(jsonInString))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}

	@Test
	public void deleteArticle() throws Exception {
		doNothing().when(articleFacade).delete(any(Long.class));
		articleController.deleteArticle(1L);
		MvcResult result = mvc.perform(delete("/v1/article/{articleId}", 1L))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}

}
