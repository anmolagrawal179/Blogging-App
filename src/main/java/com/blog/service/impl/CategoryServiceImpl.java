package com.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.blog.entity.Category;
import com.blog.exception.ResourceNotFoundException;
import com.blog.repository.CategoryRepository;
import com.blog.response.CategoryResponse;
import com.blog.response.PaginatedCategoryResponse;
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
	public PaginatedCategoryResponse getAllCategories(Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
		Page<Category> page = categoryRepository.findAll(pageable);
		List<Category> categories = page.getContent();
		List<CategoryResponse> categoryResponses = categories.stream()
				.map(category -> modelMapper.map(category, CategoryResponse.class)).collect(Collectors.toList());

		PaginatedCategoryResponse paginatedCategoryResponse = new PaginatedCategoryResponse();
		paginatedCategoryResponse.setCategories(categoryResponses);
		paginatedCategoryResponse.setPageNumber(page.getNumber());
		paginatedCategoryResponse.setPageSize(page.getSize());
		paginatedCategoryResponse.setTotalElements(page.getTotalElements());
		paginatedCategoryResponse.setTotalPages(page.getTotalPages());
		paginatedCategoryResponse.setLastPage(page.isLast());

		return paginatedCategoryResponse;
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
