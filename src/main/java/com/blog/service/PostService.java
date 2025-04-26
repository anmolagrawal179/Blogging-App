package com.blog.service;

import com.blog.response.PaginatedPostResponse;
import com.blog.response.PostResponse;

public interface PostService {

	PostResponse addPost(PostResponse postResponse, Long userId, Long categoryId);

	PaginatedPostResponse getAllPosts(Integer pageNumber, Integer pageSize);

	PostResponse getPostById(Long id);

	PaginatedPostResponse getPostsByUser(Long userId, Integer pageNumber, Integer pageSize);

	PaginatedPostResponse getPostsByCategory(Long categoryId, Integer pageNumber, Integer pageSize);

	PostResponse updatePost(PostResponse postResponse, Long id);

	void deletePost(Long id);
}
