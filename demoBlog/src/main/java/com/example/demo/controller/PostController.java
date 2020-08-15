package com.example.demo.controller;

import java.util.List;

import javax.net.ssl.SSLEngineResult.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PostDto;
import com.example.demo.service.PostService;

@RestController
@RequestMapping("/api/posts/")
//@CrossOrigin(origins = "*")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/add")
    public ResponseEntity createPost(@RequestBody PostDto postDto) {
    	
    	//System.out.println("aaaaaaaaa");
        postService.createPost(postDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDto>> showAllPosts() {
    	System.out.println("bbbbbbbbbbbb");
        return new ResponseEntity<>(postService.showAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PostDto> getSinglePost(@PathVariable @RequestBody Long id) {
        return new ResponseEntity<>(postService.readSinglePost(id), HttpStatus.OK);
    }
}
