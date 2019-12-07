package com.youngseok.webservice.domain;


import static org.hamcrest.CoreMatchers.is;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertThat;

import com.youngseok.webservice.domain.posts.Posts;
import com.youngseok.webservice.domain.posts.PostsRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

	@Autowired
	PostsRepository postsRepository;
	
	@After
	public void cleanup() {
		postsRepository.deleteAll();
	}
	
	@Test
	public void loading_post() {
		//given
		postsRepository.save(Posts.builder()
					.title("Test Title")
					.author("Test Author")
					.content("astonisher88")
					.build());
		
		//when
		List<Posts> postsList = postsRepository.findAll();
		
		//then
		Posts posts = postsList.get(0);
		assertThat(posts.getTitle(), is("Test Title"));
		assertThat(posts.getContent(), is("astonisher88"));
		
	}

}
