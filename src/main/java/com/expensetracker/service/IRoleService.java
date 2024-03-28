package com.expensetracker.service;

import java.util.List;

import com.expensetracker.common.dto.response.RoleResponse;
import com.expensetracker.common.interfaces.IService;
import com.expensetracker.domain.Role;

public interface IRoleService extends IService<Role> {

	List<RoleResponse> domainToDto(List<Role> roles);

	RoleResponse domainToDto(Role role);

}
