package com.blog.service;

import java.util.List;

import com.blog.response.PostResponse;

public interface PostService {

	PostResponse addPost(PostResponse postResponse, Long userId, Long categoryId);
	
	List<PostResponse> getAllPosts();
	
	PostResponse getPostById(Long id);
	
	PostResponse updatePost(PostResponse postResponse, Long id);
	
	void deletePost(Long id);
}
