package com.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.entity.User;
import com.blog.exception.ResourceNotFoundException;
import com.blog.repository.UserRepository;
import com.blog.response.PaginatedUserResponse;
import com.blog.response.UserResponse;
import com.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	private ModelMapper modelMapper;
	
	private PasswordEncoder passwordEncoder;


	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserResponse addUser(UserResponse userResponse) {
		User user = modelMapper.map(userResponse, User.class);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User savedUser = userRepository.save(user);
		return modelMapper.map(savedUser, UserResponse.class);
	}

	@Override
	public PaginatedUserResponse getAllUsers(Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
		Page<User> page = userRepository.findAll(pageable);
		List<User> users = page.getContent();
		List<UserResponse> userResponses = users.stream().map(user -> modelMapper.map(user, UserResponse.class))
				.collect(Collectors.toList());
		PaginatedUserResponse paginatedUserResponse = new PaginatedUserResponse();
		paginatedUserResponse.setUsers(userResponses);
		paginatedUserResponse.setPageNumber(page.getNumber());
		paginatedUserResponse.setPageSize(page.getSize());
		paginatedUserResponse.setTotalElements(page.getTotalElements());
		paginatedUserResponse.setTotalPages(page.getTotalPages());
		paginatedUserResponse.setLastPage(page.isLast());

		return paginatedUserResponse;
	}

	@Override
	public UserResponse getUserById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
		return modelMapper.map(user, UserResponse.class);
	}

	@Override
	public void deleteUser(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
		userRepository.delete(user);

	}

	@Override
	public UserResponse updateUser(UserResponse userResponse, Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));

		user.setName(userResponse.getName());
		user.setEmail(userResponse.getEmail());
		user.setPassword(userResponse.getPassword());
		user.setAbout(userResponse.getAbout());

		User savedUser = userRepository.save(user);

		return modelMapper.map(savedUser, UserResponse.class);
	}
}
