package com.upday.repository;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.upday.Application;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(classes = HelperRepositoryTest.IntegrationTest.class)
public class HelperRepositoryTest {

	@Configuration
	@EntityScan(basePackages = { "com.upday.entity" })
	@EnableAutoConfiguration(exclude = { WebMvcAutoConfiguration.class })
	protected static class IntegrationTest {
		public static void main(String[] args) {
			SpringApplication.run(Application.class, args);
		}
	}

	@Test
	public void emptyTest() {
	}

}
