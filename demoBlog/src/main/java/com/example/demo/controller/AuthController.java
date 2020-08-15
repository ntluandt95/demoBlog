package com.example.demo.controller;



import org.apache.tomcat.util.http.parser.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.DefaultCookieSerializerCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthService;
import com.example.demo.service.AuthenticationResponse;

@RestController
@RequestMapping("/api/auth")
//@CrossOrigin(origins = "*")
public class AuthController {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	AuthService authService;
	@PostMapping("/signup")
	public ResponseEntity signup(@RequestBody RegisterRequest registerRequest) {
		authService.signup(registerRequest);
        return new ResponseEntity(HttpStatus.OK);

	}
	@PostMapping("/login")
	public AuthenticationResponse login(@RequestBody LoginRequest loginRequest)
	{
		return authService.login(loginRequest);
	}
//	
//	@PostMapping("/login")
//	public String login(@RequestBody LoginRequest loginRequest) {
//		System.out.println("aaaaaaaaaaaaaaa");
//		try {
//			if(userRepository.findByUsername(loginRequest.getUsername())
//					.orElse(null).getPassword().equals(loginRequest.getPassword()))
//				//Cookie cookie = new Cookie;
//				return loginRequest.getUsername();
//			else
//				return "Fail";
//		} catch (NullPointerException e) {
//			return "Fail";
//		}
//		
//	}
	
}
