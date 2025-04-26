package com.blog.response;

import java.util.List;

import lombok.Data;

@Data
public class PaginatedCategoryResponse {

	private List<CategoryResponse> categories;
	private Integer pageNumber;
	private Integer pageSize;
	private Long totalElements;
	private Integer totalPages;
	private Boolean lastPage;

}
