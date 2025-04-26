package com.blog.service;

import java.util.List;

import com.blog.response.UserResponse;

public interface UserService {

	UserResponse addUser(UserResponse userResponse);

	List<UserResponse> getAllUsers(Integer pageNumber, Integer pageSize);

	UserResponse getUserById(Long id);

	void deleteUser(Long id);

	UserResponse updateUser(UserResponse userResponse,Long id);
}
