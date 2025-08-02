package com.example.config.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class InMemoryUserConfig {

	@Bean
	public UserDetailsService userDetailsService() {
		var user = User.withUsername("teller1")
				.password(passwordEncoder().encode("123"))
				.roles("TELLER")
				.build();
		var user2 = User.withUsername("simple")
				.password(passwordEncoder().encode("123"))
				.roles("READ")
				.build();
		return new InMemoryUserDetailsManager(user, user2);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

