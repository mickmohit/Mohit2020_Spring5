package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Post;
import com.example.demo.repository.PostRepository;

@RestController
@RequestMapping(value = "/")
public class PostController {
	
	@Autowired
	private PostRepository postRepository;
	
		
	@GetMapping("/posts")
	public List<Post> getallPosts()
	{
		return postRepository.findAll();
	}
	

	@PostMapping(value = "/saveposts")
    public Post savePost(@Valid @RequestBody Post post) {
		return postRepository.save(post);
	}
	
	@PutMapping("update/{postId}")
	public Post updatePost(@PathVariable("postId") Long id, @Valid @RequestBody Post postReq)
	{
		return postRepository.findById(id).map(post->
		{
			post.setContent(postReq.getContent());
			post.setDescription(postReq.getDescription());
			post.setTitle(postReq.getTitle());
			post.setLastUpdatedAt(postReq.getLastUpdatedAt());
			post.setPostedAt(postReq.getPostedAt());
			return postRepository.save(post);
		}).orElseThrow(null);
		
	}
	
	@DeleteMapping("/delete/{postId}")
	public List<Post> deletePost(@PathVariable("postId") Long id)
	{
		return postRepository.findById(id).map(post ->
		{
			postRepository.delete(post);
			return postRepository.findAll();
		}).orElseThrow(null);
	}

}
