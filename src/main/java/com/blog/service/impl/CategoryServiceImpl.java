package com.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.blog.entity.Category;
import com.blog.exception.ResourceNotFoundException;
import com.blog.repository.CategoryRepository;
import com.blog.response.CategoryResponse;
import com.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository;

	private ModelMapper modelMapper;

	public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
		super();
		this.categoryRepository = categoryRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public CategoryResponse addCategory(CategoryResponse categoryResponse) {

		Category category = modelMapper.map(categoryResponse, Category.class);
		Category savedCategory = categoryRepository.save(category);
		return modelMapper.map(savedCategory, CategoryResponse.class);
	}

	@Override
	public List<CategoryResponse> getAllCategories() {
		List<Category> categories = categoryRepository.findAll();
		List<CategoryResponse> categoryResponses = categories.stream()
				.map(category -> modelMapper.map(category, CategoryResponse.class)).collect(Collectors.toList());

		return categoryResponses;
	}

	@Override
	public CategoryResponse getCategoryById(Long id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));
		return modelMapper.map(category, CategoryResponse.class);
	}

	@Override
	public CategoryResponse updateCategory(CategoryResponse categoryResponse, Long id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));
		category.setTitle(categoryResponse.getTitle());
		Category savedCategory = categoryRepository.save(category);
		return modelMapper.map(savedCategory, CategoryResponse.class);
	}

	@Override
	public void deleteCategory(Long id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));
		categoryRepository.delete(category);

	}

}
