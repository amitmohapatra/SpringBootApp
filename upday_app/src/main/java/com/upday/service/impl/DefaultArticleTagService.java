package com.upday.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upday.entity.Article;
import com.upday.exception.EntityNotFoundException;
import com.upday.repository.ArticleTagRepository;
import com.upday.service.ArticleTagService;

@Service
public class DefaultArticleTagService implements ArticleTagService {

	@Autowired
	private ArticleTagRepository articleTagRepository;

	@Override
	public Iterable<Article> findArticlesByKeyword(String keyword) throws EntityNotFoundException {
		return articleTagRepository.findArticlesByKeyword(keyword);
	}

}
