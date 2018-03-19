package com.upday.service;

import com.upday.dto.ArticleAuthorData;
import com.upday.entity.Article;
import com.upday.exception.EntityNotFoundException;

public interface ArticleAuthorService {

	Iterable<Article> findArticlesByAuthorAttribute(ArticleAuthorData articleAuthorData) throws EntityNotFoundException;

}