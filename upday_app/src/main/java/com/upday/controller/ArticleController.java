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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.upday.dto.ArticleAuthorData;
import com.upday.dto.ArticleData;
import com.upday.dto.ArticleDateData;
import com.upday.exception.ConstraintsViolationException;
import com.upday.exception.EntityNotFoundException;
import com.upday.facade.ArticleAuthorFacade;
import com.upday.facade.ArticleFacade;
import com.upday.facade.ArticleTagFacade;

/**
 * All operations with a article will be routed by this controller. 
 * Created by Amit Mohapatra on 03/17/18.
 */
@RestController
@RequestMapping("v1/article")
public class ArticleController {

	@Autowired
	private ArticleFacade articleFacade;

	@Autowired
	private ArticleAuthorFacade articleAuthorFacade;

	@Autowired
	private ArticleTagFacade articleTagFacade;

	@RequestMapping(value = "/{articleId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ArticleData> getArticle(@Valid @PathVariable long articleId) throws EntityNotFoundException {
		return new ResponseEntity<>(articleFacade.findArticleById(articleId), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<ArticleData>> getAllArticles() {
		return new ResponseEntity<>(articleFacade.findAllArticles(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ArticleData> createArticle(@Valid @RequestBody ArticleData articleData)
			throws ConstraintsViolationException {
		return new ResponseEntity<>(articleFacade.create(articleData), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> updateArticle(@Valid @RequestBody ArticleData articleData)
			throws EntityNotFoundException {
		articleFacade.update(articleData);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{articleId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteArticle(@Valid @PathVariable long articleId) throws EntityNotFoundException {
		articleFacade.delete(articleId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/author", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<ArticleData>> getAllArticlesByAuthor(
			@Valid @RequestBody ArticleAuthorData articleAuthorData) {
		return new ResponseEntity<>(articleAuthorFacade.findArticlesByAuthorAttribute(articleAuthorData),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/date", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<ArticleData>> findArticleByDates(@Valid @RequestBody ArticleDateData articleDateData) {
		return new ResponseEntity<>(articleFacade.findArticlesByDate(articleDateData), HttpStatus.OK);
	}

	@RequestMapping(value = "/keyword", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<ArticleData>> getArticleByKeyword(@RequestParam(value = "search", required = false) String keyword)
			throws EntityNotFoundException {
		return new ResponseEntity<>(articleTagFacade.findArticlesByKeyword(keyword), HttpStatus.OK);
	}
}
