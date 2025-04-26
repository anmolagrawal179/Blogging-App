package com.blog.service;

import com.blog.response.CommentResponse;

public interface CommentService {

	CommentResponse addComment(CommentResponse commentResponse, Long postId);
	
	void deleteComment(Long id);
}
