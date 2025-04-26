package com.blog.response;

import java.util.List;

import lombok.Data;

@Data
public class PaginatedPostResponse {

	private List<PostResponse> posts;
	private Integer pageNumber;
	private Integer pageSize;
	private Long totalElements;
	private Integer totalPages;
	private Boolean lastPage;

}
