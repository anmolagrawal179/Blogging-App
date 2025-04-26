package com.blog.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryResponse {

	private Long id;
	
	@NotBlank
	private String title;

}
