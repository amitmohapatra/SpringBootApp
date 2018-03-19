package com.upday.facade;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.upday.dto.ArticleData;
import com.upday.exception.EntityNotFoundException;

public interface ArticleTagFacade {

	List<ArticleData> findArticlesByKeyword(@Param("keyword") String keyword) throws EntityNotFoundException;
}
