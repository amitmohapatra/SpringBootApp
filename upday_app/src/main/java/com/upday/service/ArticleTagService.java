package com.upday.service;

import org.springframework.data.repository.query.Param;

import com.upday.entity.Article;
import com.upday.exception.EntityNotFoundException;

public interface ArticleTagService {
	Iterable<Article> findArticlesByKeyword(@Param("keyword") String keyword) throws EntityNotFoundException;
}
