package com.blog.service;

import java.util.List;

import com.blog.response.PostResponse;

public interface PostService {

	PostResponse addPost(PostResponse postResponse, Long userId, Long categoryId);

	List<PostResponse> getAllPosts(Integer pageNumber, Integer pageSize);

	PostResponse getPostById(Long id);

	List<PostResponse> getPostsByUser(Long userId, Integer pageNumber, Integer pageSize);

	List<PostResponse> getPostsByCategory(Long categoryId, Integer pageNumber, Integer pageSize);

	PostResponse updatePost(PostResponse postResponse, Long id);

	void deletePost(Long id);
}
