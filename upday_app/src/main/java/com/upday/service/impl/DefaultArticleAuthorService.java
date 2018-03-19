package com.upday.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upday.dto.ArticleAuthorData;
import com.upday.entity.Article;
import com.upday.exception.EntityNotFoundException;
import com.upday.repository.ArticleAuthorRepository;
import com.upday.service.ArticleAuthorService;

@Service
public class DefaultArticleAuthorService implements ArticleAuthorService {

	@Autowired
	private ArticleAuthorRepository articleAuthorRepository;

	@Override
	public Iterable<Article> findArticlesByAuthorAttribute(ArticleAuthorData articleAuthorData)
			throws EntityNotFoundException {
		return articleAuthorRepository.findArticlesByAuthorAttribute(articleAuthorData);
	}

}
