package com.prgjesusindustry.apivendas.resources;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prgjesusindustry.apivendas.security.JWTUtil;
import com.prgjesusindustry.apivendas.security.UserSS;
import com.prgjesusindustry.apivendas.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthResource {
	
	@Autowired
	private JWTUtil jwtUtil;

	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserService.authenticated();
		String token = jwtUtil.genarationToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}
}
