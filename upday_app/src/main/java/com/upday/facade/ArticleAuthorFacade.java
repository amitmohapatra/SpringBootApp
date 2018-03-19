package com.upday.facade;

import java.util.List;

import com.upday.dto.ArticleAuthorData;
import com.upday.dto.ArticleData;
import com.upday.exception.EntityNotFoundException;

public interface ArticleAuthorFacade {

	List<ArticleData> findArticlesByAuthorAttribute(ArticleAuthorData articleAuthorData) throws EntityNotFoundException;

}