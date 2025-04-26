package com.blog.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentResponse {

	private Long id;
	
	@NotBlank
	private String content;
}
