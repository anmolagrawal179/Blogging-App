package com.blog.controller;

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

import com.blog.response.PaginatedUserResponse;
import com.blog.response.UserResponse;
import com.blog.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<UserResponse> addUser(@RequestBody @Valid UserResponse userResponse) {
		return new ResponseEntity<UserResponse>(userService.addUser(userResponse), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<PaginatedUserResponse> getAllUsers(
			@RequestParam(required = false, defaultValue = "1") Integer pageNumber,
			@RequestParam(required = false, defaultValue = "10") Integer pageSize) {
		return ResponseEntity.ok(userService.getAllUsers(pageNumber, pageSize));
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
		return ResponseEntity.ok(userService.getUserById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserResponse> updateUser(@RequestBody @Valid UserResponse userResponse,
			@PathVariable Long id) {
		return ResponseEntity.ok(userService.updateUser(userResponse, id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return ResponseEntity.ok(Map.of("deleted", Boolean.TRUE));
	}
}
