package com.example.auth;

import com.example.auth.dto.AuthRequest;
import com.example.util.JwtUtil;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthenticationManager authManager;

	private final JwtUtil jwtUtil;

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody AuthRequest request) {
		Authentication authentication = authManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		if (authentication.isAuthenticated()) {
			String token = jwtUtil.generateToken((UserDetails) authentication.getPrincipal());
			return ResponseEntity.ok(token);
		}

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.build();
	}
}

