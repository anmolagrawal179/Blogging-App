package com.blog.response;

import java.util.List;

import lombok.Data;

@Data
public class PaginatedUserResponse {

	private List<UserResponse> users;
	private Integer pageNumber;
	private Integer pageSize;
	private Long totalElements;
	private Integer totalPages;
	private Boolean lastPage;
}
