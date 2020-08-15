package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.exception.SpringBlogException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtProvider;

import io.jsonwebtoken.security.InvalidKeyException;


@Service
public class AuthService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtProvider jwtprovider;
	public void signup(RegisterRequest registerRequest)
	{
		User user = new User();
		user.setUsername(registerRequest.getUsername());
        user.setPassword(encodePassword(registerRequest.getPassword()));
		user.setEmail(registerRequest.getEmail());
		userRepository.save(user);
	}
 	private String encodePassword(String password) {return passwordEncoder.encode(password); }
 	public AuthenticationResponse login(LoginRequest loginRequest){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String authenticationToken = jwtprovider.generateToken(authenticate);
        return new AuthenticationResponse(authenticationToken, loginRequest.getUsername());
    }
	public java.util.Optional<org.springframework.security.core.userdetails.User> getCurrentUser(){
		org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return java.util.Optional.of(principal);
	}
	
	
}
