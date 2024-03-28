package com.expensetracker.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.expensetracker.common.dto.response.UserResponse;
import com.expensetracker.common.interfaces.IService;
import com.expensetracker.domain.User;

public interface IUserService extends IService<User> {

	User findByUserName(String username);

	Page<UserResponse> domainToDto(Page<User> page);

	List<UserResponse> domainToDto(List<User> users);

	UserResponse domainToDto(User user);

}
