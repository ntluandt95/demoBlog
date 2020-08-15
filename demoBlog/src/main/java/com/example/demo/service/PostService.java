package com.example.demo.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PostDto;
import com.example.demo.exception.PostNotFoundException;
import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private AuthService authService;
	@Autowired
	private PostRepository postRepository;
	
	public void createPost(PostDto postDto)
	{
		Post post = new Post();
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		User username = authService.getCurrentUser().orElseThrow(() -> 
			new IllegalArgumentException("No User logged in"));
		post.setUsername(username.getUsername());
		post.setCreatedOn(Instant.now());
		postRepository.save(post);
	}

	public List<PostDto> showAllPosts() {
		List<Post> posts = postRepository.findAll();
		List<PostDto> postDtos = new ArrayList<PostDto>();
		for (Post post : posts) {
			postDtos.add(mapFromPostToDto(post));
		}
		return postDtos;
	}
	
	public PostDto readSinglePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("For id " + id));
        return mapFromPostToDto(post);
    }
	
	private PostDto mapFromPostToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setUsername(post.getUsername());
        return postDto;
    }

    private Post mapFromDtoToPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        User loggedInUser = authService.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        post.setCreatedOn(Instant.now());
        post.setUsername(loggedInUser.getUsername());
        post.setUpdatedOn(Instant.now());
        return post;
    }
    
   
	
	
}
