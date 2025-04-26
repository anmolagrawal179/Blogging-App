package com.blog.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.response.CategoryResponse;
import com.blog.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	private CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}

	@PostMapping
	public ResponseEntity<CategoryResponse> addCategory(@RequestBody @Valid CategoryResponse categoryResponse) {
		return new ResponseEntity<CategoryResponse>(categoryService.addCategory(categoryResponse), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<CategoryResponse>> getAllCategories(
			@RequestParam(required = false, defaultValue = "1") Integer pageNumber,
			@RequestParam(required = false, defaultValue = "10") Integer pageSize) {
		return ResponseEntity.ok(categoryService.getAllCategories(pageNumber, pageSize));
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
		return ResponseEntity.ok(categoryService.getCategoryById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<CategoryResponse> updateCategory(@RequestBody @Valid CategoryResponse categoryResponse,
			@PathVariable Long id) {
		return ResponseEntity.ok(categoryService.updateCategory(categoryResponse, id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
		categoryService.deleteCategory(id);
		return ResponseEntity.ok(Map.of("deleted", Boolean.TRUE));
	}
}
