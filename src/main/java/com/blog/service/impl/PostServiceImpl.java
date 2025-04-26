package com.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.entity.User;
import com.blog.exception.ResourceNotFoundException;
import com.blog.repository.CategoryRepository;
import com.blog.repository.PostRepository;
import com.blog.repository.UserRepository;
import com.blog.response.PaginatedPostResponse;
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
		Post post = modelMapper.map(postResponse, Post.class);

		post.setUser(user);
		post.setCategory(category);

		Post savedPost = postRepository.save(post);

		return modelMapper.map(savedPost, PostResponse.class);

	}

	@Override
	public PaginatedPostResponse getAllPosts(Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
		Page<Post> page = postRepository.findAll(pageable);
		List<Post> posts = page.getContent();

		List<PostResponse> postResponses = posts.stream().map(post -> modelMapper.map(post, PostResponse.class))
				.collect(Collectors.toList());

		PaginatedPostResponse paginatedPostResponse = new PaginatedPostResponse();
		paginatedPostResponse.setPosts(postResponses);
		paginatedPostResponse.setPageNumber(page.getNumber());
		paginatedPostResponse.setPageSize(page.getSize());
		paginatedPostResponse.setTotalElements(page.getTotalElements());
		paginatedPostResponse.setTotalPages(page.getTotalPages());
		paginatedPostResponse.setLastPage(page.isLast());

		return paginatedPostResponse;
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

		Post savedPost = postRepository.save(post);

		return modelMapper.map(savedPost, PostResponse.class);
	}

	@Override
	public void deletePost(Long id) {

		Post post = postRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + id));
		postRepository.delete(post);

	}

	@Override
	public PaginatedPostResponse getPostsByUser(Long userId, Integer pageNumber, Integer pageSize) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
		Page<Post> page = postRepository.findByUser(user, pageable);
		List<Post> posts = page.getContent();
		List<PostResponse> postResponses = posts.stream().map(post -> modelMapper.map(post, PostResponse.class))
				.collect(Collectors.toList());
		PaginatedPostResponse paginatedPostResponse = new PaginatedPostResponse();
		paginatedPostResponse.setPosts(postResponses);
		paginatedPostResponse.setPageNumber(page.getNumber());
		paginatedPostResponse.setPageSize(page.getSize());
		paginatedPostResponse.setTotalElements(page.getTotalElements());
		paginatedPostResponse.setTotalPages(page.getTotalPages());
		paginatedPostResponse.setLastPage(page.isLast());

		return paginatedPostResponse;
	}

	@Override
	public PaginatedPostResponse getPostsByCategory(Long categoryId, Integer pageNumber, Integer pageSize) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + categoryId));
		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
		Page<Post> page = postRepository.findByCategory(category, pageable);
		List<Post> posts = page.getContent();
		List<PostResponse> postResponses = posts.stream().map(post -> modelMapper.map(post, PostResponse.class))
				.collect(Collectors.toList());
		PaginatedPostResponse paginatedPostResponse = new PaginatedPostResponse();
		paginatedPostResponse.setPosts(postResponses);
		paginatedPostResponse.setPageNumber(page.getNumber());
		paginatedPostResponse.setPageSize(page.getSize());
		paginatedPostResponse.setTotalElements(page.getTotalElements());
		paginatedPostResponse.setTotalPages(page.getTotalPages());
		paginatedPostResponse.setLastPage(page.isLast());

		return paginatedPostResponse;
	}

}
