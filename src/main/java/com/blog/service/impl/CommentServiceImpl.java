package com.blog.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.blog.entity.Comment;
import com.blog.entity.Post;
import com.blog.exception.ResourceNotFoundException;
import com.blog.repository.CommentRepository;
import com.blog.repository.PostRepository;
import com.blog.response.CommentResponse;
import com.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	private CommentRepository commentRepository;

	private PostRepository postRepository;

	private ModelMapper modelMapper;

	public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository,
			ModelMapper modelMapper) {
		super();
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public CommentResponse addComment(CommentResponse commentResponse, Long postId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + postId));
		Comment comment = modelMapper.map(commentResponse, Comment.class);
		comment.setPost(post);

		Comment savedComment = commentRepository.save(comment);

		return modelMapper.map(savedComment, CommentResponse.class);
	}

	@Override
	public void deleteComment(Long id) {

		Comment comment = commentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + id));

		commentRepository.delete(comment);

	}

}
