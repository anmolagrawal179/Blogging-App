package com.blog.response;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserResponse {

	@Id
	private Long id;
	
	@NotBlank
	private String name;
	
	@Email
	@NotBlank
	private String email;
	
	@NotBlank
	private String password;
	
	private String about;
}
