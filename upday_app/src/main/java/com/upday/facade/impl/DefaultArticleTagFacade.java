package com.upday.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upday.dto.ArticleData;
import com.upday.exception.EntityNotFoundException;
import com.upday.facade.ArticleTagFacade;
import com.upday.generator.ArticleGenerator;
import com.upday.service.ArticleTagService;

@Service
public class DefaultArticleTagFacade implements ArticleTagFacade {

	@Autowired
	private ArticleTagService articleTagService;
	
	@Override
	public List<ArticleData> findArticlesByKeyword(String keyword) throws EntityNotFoundException {	
		return ArticleGenerator.generate(articleTagService.findArticlesByKeyword(keyword));
	}
}
