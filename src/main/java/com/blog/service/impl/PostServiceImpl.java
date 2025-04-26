package com.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.entity.User;
import com.blog.exception.ResourceNotFoundException;
import com.blog.repository.CategoryRepository;
import com.blog.repository.PostRepository;
import com.blog.repository.UserRepository;
import com.blog.response.PostResponse;
import com.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	private PostRepository postRepository;

	private ModelMapper modelMapper;
	
	private UserRepository userRepository;
	
	private CategoryRepository categoryRepository;

	public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper, UserRepository userRepository,
			CategoryRepository categoryRepository) {
		super();
		this.postRepository = postRepository;
		this.modelMapper = modelMapper;
		this.userRepository = userRepository;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public PostResponse addPost(PostResponse postResponse, Long userId, Long categoryId) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
		
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + categoryId));
		Post post=modelMapper.map(postResponse, Post.class);
		
		post.setUser(user);
		post.setCategory(category);
		
		Post savedPost=postRepository.save(post);
		
		return modelMapper.map(savedPost, PostResponse.class);
		
	}

	@Override
	public List<PostResponse> getAllPosts() {
		List<Post> posts = postRepository.findAll();
		List<PostResponse> postResponses = posts.stream().map(post -> modelMapper.map(post, PostResponse.class))
				.collect(Collectors.toList());

		return postResponses;
	}

	@Override
	public PostResponse getPostById(Long id) {

		Post post = postRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + id));
		return modelMapper.map(post, PostResponse.class);
	}

	@Override
	public PostResponse updatePost(PostResponse postResponse, Long id) {
		Post post = postRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + id));
		post.setTitle(postResponse.getTitle());
		post.setDescription(postResponse.getDescription());
		
		Post savedPost=postRepository.save(post);
		
		return modelMapper.map(savedPost, PostResponse.class);
	}

	@Override
	public void deletePost(Long id) {

		Post post = postRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + id));
		postRepository.delete(post);

	}

}
