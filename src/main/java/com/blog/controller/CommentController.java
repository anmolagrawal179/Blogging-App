package com.blog.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.response.CommentResponse;
import com.blog.service.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

	private CommentService commentService;

	public CommentController(CommentService commentService) {
		super();
		this.commentService = commentService;
	}

	@PostMapping("/post/{id}")
	public ResponseEntity<CommentResponse> addComment(@RequestBody @Valid CommentResponse commentResponse,
			@PathVariable Long id) {
		return new ResponseEntity<CommentResponse>(commentService.addComment(commentResponse, id), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteComment(@PathVariable Long id) {
		commentService.deleteComment(id);
		return ResponseEntity.ok(Map.of("deleted", Boolean.TRUE));
	}
}
