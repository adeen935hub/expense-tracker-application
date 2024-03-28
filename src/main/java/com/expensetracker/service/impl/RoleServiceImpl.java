package com.expensetracker.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expensetracker.common.dto.response.RoleResponse;
import com.expensetracker.common.persistence.service.AbstractRawService;
import com.expensetracker.domain.Role;
import com.expensetracker.repository.RoleRepository;
import com.expensetracker.service.IRoleService;

@Service
@Transactional
public class RoleServiceImpl extends AbstractRawService<Role> implements IRoleService {
	@Autowired
	private RoleRepository repo;

	@Override
	protected JpaRepository<Role, Long> getDao() {
		return repo;
	}

	@Override
	protected JpaSpecificationExecutor<Role> getSpecificationExecutor() {
		return repo;
	}

	@Override
	public List<RoleResponse> domainToDto(List<Role> roles) {

		return roles.stream().map(this::domainToDto).collect(Collectors.toList());
	}

	@Override
	public RoleResponse domainToDto(Role role) {
		try {
			RoleResponse roleResponse = new RoleResponse();
			roleResponse.setDescription(role.getDescription());
			roleResponse.setId(role.getId());
			roleResponse.setName(role.getName());
			return roleResponse;
		} catch (NullPointerException e) {
			// In case role can be null on .that time we will return null
			// Why we using try catch , it reduce unwanted null check for every time
			return null;
		}
	}

}
