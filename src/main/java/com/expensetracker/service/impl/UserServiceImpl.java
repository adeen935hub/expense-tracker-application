package com.expensetracker.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expensetracker.common.dto.response.UserResponse;
import com.expensetracker.common.persistence.service.AbstractRawService;
import com.expensetracker.domain.User;
import com.expensetracker.repository.UserRepository;
import com.expensetracker.service.IRoleService;
import com.expensetracker.service.IUserService;

@Service
@Transactional
public class UserServiceImpl extends AbstractRawService<User> implements IUserService {
	@Autowired
	private UserRepository repo;

	@Autowired
	private IRoleService roleService;

	@Override
	protected JpaRepository<User, Long> getDao() {
		return repo;
	}

	@Override
	protected JpaSpecificationExecutor<User> getSpecificationExecutor() {
		return repo;
	}

	@Override
	public User findByUserName(String username) {
		return repo.findByUserName(username);
	}

	@Override
	public Page<UserResponse> domainToDto(Page<User> pages) {

		return pages.map(this::domainToDto);
	}

	@Override
	public List<UserResponse> domainToDto(List<User> users) {

		return users.stream().map(this::domainToDto).collect(Collectors.toList());
	}

	@Override
	public UserResponse domainToDto(User user) {
		try {
			UserResponse userResponse = new UserResponse();
			userResponse.setActive(user.isActive());
			userResponse.setEmail(user.getEmail());
			userResponse.setId(user.getId());
			userResponse.setUserName(user.getUserName());
			userResponse.setRoles(roleService.domainToDto(user.getRoles()));
			return userResponse;
		} catch (NullPointerException e) {
			// In case user can be null on .that time we will return null
			// Why we using try catch , it reduce unwanted null check for every time
			return null;
		}

	}

}
