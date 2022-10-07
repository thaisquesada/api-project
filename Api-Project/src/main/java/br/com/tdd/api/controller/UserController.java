package br.com.tdd.api.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import br.com.tdd.api.dto.UserDTO;
import br.com.tdd.api.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	private static final String ID = "/{id}";

	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper mapper;

	@GetMapping(value = ID)
	public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(mapper.map(userService.findById(id), UserDTO.class));
	}
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		return ResponseEntity.ok().body(userService.findAll().stream().map(x -> mapper.map(x, UserDTO.class)).collect(Collectors.toList()));
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> create(@RequestBody UserDTO obj) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID).buildAndExpand(userService.create(obj).getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = ID)
	public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO obj) {
		obj.setId(id);
		return ResponseEntity.ok().body(mapper.map(userService.update(obj), UserDTO.class));
	}
	
	@DeleteMapping(value = ID)
	public ResponseEntity<UserDTO> delete(@PathVariable Long id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}