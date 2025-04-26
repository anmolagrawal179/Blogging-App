package com.blog.service;

import com.blog.response.PaginatedUserResponse;
import com.blog.response.UserResponse;

public interface UserService {

	UserResponse addUser(UserResponse userResponse);

	PaginatedUserResponse getAllUsers(Integer pageNumber, Integer pageSize);

	UserResponse getUserById(Long id);

	void deleteUser(Long id);

	UserResponse updateUser(UserResponse userResponse,Long id);
}
