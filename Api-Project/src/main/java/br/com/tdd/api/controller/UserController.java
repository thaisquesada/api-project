package br.com.tdd.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tdd.api.entity.UserEntity;
import br.com.tdd.api.services.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<UserEntity> findById(@PathVariable long id) {
		return ResponseEntity.ok().body(userService.findById(id));
	}
	
}
