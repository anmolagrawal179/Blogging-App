package com.blog.service;

import java.util.List;

import com.blog.response.CategoryResponse;

public interface CategoryService {

	CategoryResponse addCategory(CategoryResponse categoryResponse);

	List<CategoryResponse> getAllCategories();

	CategoryResponse getCategoryById(Long id);

	CategoryResponse updateCategory(CategoryResponse categoryResponse, Long id);

	void deleteCategory(Long id);

}
