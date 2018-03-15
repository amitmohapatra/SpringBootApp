package com.upday.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.upday.dto.ArticleData;
import com.upday.exception.ArticleNotFoundException;
import com.upday.facade.ArticleFacade;

/**
 * All operations with a article will be routed by this controller. Created by
 * Amit Mohapatra on 3/17/18.
 * <p/>
 */
@RestController
@RequestMapping("v1/article")
public class ArticleController {

	@Autowired
	private ArticleFacade articleFacade;

	@RequestMapping(value = "/{articleId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ArticleData> getArticle(@Valid @PathVariable long articleId) throws ArticleNotFoundException {
		return new ResponseEntity<>(articleFacade.findArticleById(articleId), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<ArticleData>> getAllArticles() {
		return new ResponseEntity<>(articleFacade.findAllArticles(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ArticleData> createArticle(@Valid @RequestBody ArticleData articleData)
			throws ArticleNotFoundException {
		return new ResponseEntity<>(articleFacade.create(articleData), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> updateArticle(@Valid @RequestBody ArticleData articleData)
			throws ArticleNotFoundException {
		articleFacade.update(articleData);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{articleId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteArticle(@Valid @PathVariable long articleId) throws ArticleNotFoundException {
		articleFacade.delete(articleId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
