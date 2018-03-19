package com.upday.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.upday.entity.Article;
import com.upday.entity.ArticleTag;

public interface ArticleTagRepository extends CrudRepository<ArticleTag, Long> {

	@Query("select distinct a from Article a join a.tags b where b like concat('%',:keyword,'%') and a.deleted = FALSE")
	List<Article> findArticlesByKeyword(@Param("keyword") String keyword);

}