package com.upday.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.upday.dto.ArticleAuthorData;
import com.upday.entity.Article;
import com.upday.entity.ArticleAuthor;


public interface ArticleAuthorRepository extends CrudRepository<ArticleAuthor, Long> {
	
	@Query("select distinct a from Article a join a.authors b where b.username=:#{#articleAuthorData.username} and a.deleted = FALSE")
	List<Article> findArticlesByAuthorAttribute(@Param("articleAuthorData") ArticleAuthorData articleAuthorData);
}