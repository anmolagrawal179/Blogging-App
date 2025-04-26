package com.blog.service;

import com.blog.response.CategoryResponse;
import com.blog.response.PaginatedCategoryResponse;

public interface CategoryService {

	CategoryResponse addCategory(CategoryResponse categoryResponse);

	PaginatedCategoryResponse getAllCategories(Integer pageNumber, Integer pageSize);

	CategoryResponse getCategoryById(Long id);

	CategoryResponse updateCategory(CategoryResponse categoryResponse, Long id);

	void deleteCategory(Long id);

}
