package com.upday.repository;

import com.upday.entity.Article;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Amit Mohapatra on 3/17/18.
 */
public interface ArticleRepository extends CrudRepository<Article, Long> {
}
