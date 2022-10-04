package br.com.tdd.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tdd.api.entity.UserEntity;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@GetMapping(value = "/{id}")
	public ResponseEntity<UserEntity> findById(@PathVariable long id) {
		
		return ResponseEntity.ok().body(new UserEntity(1, "Thais", "thais@email.com", "123"));
	}
	
}
