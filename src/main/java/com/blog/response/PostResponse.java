package com.blog.response;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PostResponse {

	private Long id;
	
	@NotBlank
	private String title;
	
	private String description;
	private UserResponse user;
	private CategoryResponse category;
	private List<CommentResponse> comments;
}
