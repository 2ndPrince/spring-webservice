package com.youngseok.webservice.domain;


import static org.hamcrest.CoreMatchers.is;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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
	
	@Test
	public void register_BaseTimeEntity() {
		//given
        LocalDateTime now = LocalDateTime.now();
        postsRepository.save(Posts.builder()
                .title("테스트 게시글")
                .content("테스트 본문")
                .author("jojoldu@gmail.com")
                .build());
        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertTrue(posts.getCreatedDate().isAfter(now));
        assertTrue(posts.getModifiedDate().isAfter(now));
	}

}
