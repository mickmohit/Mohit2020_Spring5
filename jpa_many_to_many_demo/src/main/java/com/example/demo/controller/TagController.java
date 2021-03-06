package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Post;
import com.example.demo.entity.Tag;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.TagRepository;

@RestController
@RequestMapping(value = "/")
public class TagController {

	
	@Autowired
    private TagRepository tagRepository;

	@Autowired
    private PostRepository postRepository;

		
	@GetMapping("/tags")
	public List<Tag> getallTags()
	{
		return tagRepository.findAll();
	}
	
	@GetMapping("/post/{postId}")
	public List<Tag> getallTags(@PathVariable("postId") Long id)
	{
		return	tagRepository.findByPosts_Id(id);
	}
	
	@GetMapping("/tagsv2/{tagId}")
	public List<Tag> getallTagsv2(@PathVariable("tagId") Long id)
	{
		return	tagRepository.findByTid(id);
	}
	
	@GetMapping("/tagsv3/{tagId}")
	public List<Tag> getallTagsv3(@PathVariable("tagId") Long id)
	{
		return	tagRepository.findByTagi(id);
	}

	@PostMapping(value = "/savetags")
    public Tag saveTag(@Valid @RequestBody Tag tag) {
		return tagRepository.save(tag);
	}
	
	@PutMapping("updateTag/{tagId}")
	public Tag updateTag(@PathVariable("tagId") Long id, @Valid @RequestBody Tag tagReq)
	{
		return tagRepository.findById(id).map(tag->
		{
			tag.setName(tagReq.getName());
			return tagRepository.save(tag);
		}).orElseThrow(null);
		
	}
	
	@DeleteMapping("/deleteTag/{tagId}")
	public List<Tag> deleteTag(@PathVariable("tagId") Long id)
	{
		return tagRepository.findById(id).map(tag ->
		{
			tagRepository.delete(tag);
			return tagRepository.findAll();
		}).orElseThrow(null);
	}

}