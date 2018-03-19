package com.upday.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upday.dto.ArticleAuthorData;
import com.upday.dto.ArticleData;
import com.upday.entity.ApplicationUser;
import com.upday.exception.EntityNotFoundException;
import com.upday.facade.ArticleAuthorFacade;
import com.upday.generator.ArticleGenerator;
import com.upday.service.ArticleAuthorService;
import com.upday.service.UserService;

@Service
public class DefaultArticleAuthorFacade implements ArticleAuthorFacade {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ArticleAuthorService  articleAuthorService;
	
	@Override
	public List<ArticleData> findArticlesByAuthorAttribute(ArticleAuthorData articleAuthorData)
			throws EntityNotFoundException {
		ApplicationUser checkUser = userService.findByUsername(articleAuthorData.getUsername());
		if (checkUser == null) {
			throw new EntityNotFoundException(
					"Author does not exist with username : " + articleAuthorData.getUsername());
		}
		return ArticleGenerator.generate(articleAuthorService.findArticlesByAuthorAttribute(articleAuthorData));
	
	}

}
