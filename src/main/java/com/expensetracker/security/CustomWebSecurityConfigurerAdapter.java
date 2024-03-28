package com.expensetracker.security;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class CustomWebSecurityConfigurerAdapter {//

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/api/v1/**").hasAnyRole("USER", "ADMIN").antMatchers("/login/**")
				.anonymous().anyRequest().authenticated().and().httpBasic().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.ALWAYS).and().csrf().disable();
		http.logout(logout -> logout.logoutUrl("/auth/logout").addLogoutHandler((request, response, auth) -> {
			for (Cookie cookie : request.getCookies()) {
				String cookieName = cookie.getName();
				Cookie cookieToDelete = new Cookie(cookieName, null);
				cookieToDelete.setMaxAge(0);
				response.addCookie(cookieToDelete);
			}
		}));
		return http.build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}