package br.com.tdd.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tdd.api.dto.UserDTO;
import br.com.tdd.api.services.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UserService userService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable long id) {
		return ResponseEntity.ok().body(mapper.map(userService.findById(id), UserDTO.class));
	}
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		return ResponseEntity.ok().body(userService.findAll().stream().map(x -> mapper.map(x, UserDTO.class)).collect(Collectors.toList()));
	}
	
}
