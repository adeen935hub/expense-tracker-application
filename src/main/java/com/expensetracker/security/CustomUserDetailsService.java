package com.expensetracker.security;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.expensetracker.domain.User;
import com.expensetracker.service.IUserService;
import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.Collections2;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private IUserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		final Function<Object, String> toStringFunction = Functions.toStringFunction();
		final Collection<String> rolesToString = Collections2.transform(user.getRoles(), toStringFunction);
		final String[] roleStringsAsArray = rolesToString.toArray(new String[rolesToString.size()]);
		final List<GrantedAuthority> auths = AuthorityUtils.createAuthorityList(roleStringsAsArray);

		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), auths);
	}
}
