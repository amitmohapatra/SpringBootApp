package com.upday;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import com.upday.dto.ArticleData;
import com.upday.entity.ApplicationUser;
import com.upday.entity.Article;

@RunWith(MockitoJUnitRunner.class)
public abstract class HelperTest {

	private ApplicationUser user = new ApplicationUser("myusername", "mypassword", "myname");
	private ApplicationUser user1 = new ApplicationUser("myusername1", "mypassword1", "myname1");
	private Set<String> tags = Stream.of("mobile", "phone").collect(Collectors.toCollection(HashSet::new));
	private List<ApplicationUser> authors = new ArrayList<>(Arrays.asList(user, user1));
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

    public HelperTest() {
	    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("myusername", "myusername"));
    }
    
	public Article getArticle() {
		Article article = new Article();
		article.setId(1L);
		article.setHeader("Apple 10");
		article.setDescription("Apple 10 camera");
		article.setText("Apple 10 photos are awesome");
		article.setCreated_by("myusername");
		article.setDateCreated(ZonedDateTime.now());
		article.setDateUpdated(ZonedDateTime.now());
		article.setTags(tags);
		article.setAuthors(authors);
		return article;
	}

	public ArticleData getArticleData() {
		Article source = getArticle();
		return ArticleData.newArticleDataBuilder().setId(source.getId()).setCreatedByUsername(source.getCreated_by())
				.setHeader(source.getHeader()).setDescription(source.getDescription()).setText(source.getText())
				.setAuthors(
						source.getAuthors().stream().map(author -> author.getUsername()).collect(Collectors.toList()))
				.setTags(source.getTags()).setCreatedDate(formatter.format(source.getDateCreated()))
				.setUpdatedDate(formatter.format(source.getDateUpdated())).build();
	}
	
	public ApplicationUser getApplicationUser() {
		return user;
	}

}
