package com.blog.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.response.PaginatedPostResponse;
import com.blog.response.PostResponse;
import com.blog.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	private PostService postService;

	public PostController(PostService postService) {
		super();
		this.postService = postService;
	}

	@PostMapping("/user/{userId}/category/{categoryId}")
	public ResponseEntity<PostResponse> addPost(@RequestBody @Valid PostResponse postResponse,
			@PathVariable Long userId, @PathVariable Long categoryId) {
		return new ResponseEntity<PostResponse>(postService.addPost(postResponse, userId, categoryId),
				HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<PaginatedPostResponse> getAllPosts(
			@RequestParam(required = false, defaultValue = "1") Integer pageNumber,
			@RequestParam(required = false, defaultValue = "10") Integer pageSize) {
		return ResponseEntity.ok(postService.getAllPosts(pageNumber, pageSize));
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostResponse> getPostById(@PathVariable Long id) {
		return ResponseEntity.ok(postService.getPostById(id));
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<PaginatedPostResponse> getPostByUser(@PathVariable Long userId,
			@RequestParam(required = false, defaultValue = "1") Integer pageNumber,
			@RequestParam(required = false, defaultValue = "10") Integer pageSize) {
		return ResponseEntity.ok(postService.getPostsByUser(userId, pageNumber, pageSize));
	}

	@GetMapping("/category/{categoryId}")
	public ResponseEntity<PaginatedPostResponse> getPostByCategory(@PathVariable Long categoryId,
			@RequestParam(required = false, defaultValue = "1") Integer pageNumber,
			@RequestParam(required = false, defaultValue = "10") Integer pageSize) {
		return ResponseEntity.ok(postService.getPostsByCategory(categoryId, pageNumber, pageSize));
	}

	@PutMapping("/{id}")
	public ResponseEntity<PostResponse> updatePost(@RequestBody @Valid PostResponse postResponse,
			@PathVariable Long id) {
		return ResponseEntity.ok(postService.updatePost(postResponse, id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePost(@PathVariable Long id) {
		postService.deletePost(id);
		return ResponseEntity.ok(Map.of("deleted", Boolean.TRUE));
	}
}
