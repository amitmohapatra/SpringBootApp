package com.upday.repository;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.upday.entity.Article;

/**
 * Created by Amit Mohapatra on 3/17/18.
 */
public interface ArticleRepository extends CrudRepository<Article, Long> {

	@Query("select distinct a from Article a where a.dateCreated BETWEEN :begindate and :enddate and a.deleted = FALSE")
	List<Article> findArticlesByDate(@Param("begindate") ZonedDateTime begindate,
			@Param("enddate") ZonedDateTime enddate);

	@Query("select distinct a from Article a where a.deleted = FALSE")
	List<Article> findAllArticles();
}
