package br.com.tdd.api.service;

import java.util.List;

import br.com.tdd.api.dto.UserDTO;
import br.com.tdd.api.entity.UserEntity;

public interface UserService {
	
	UserEntity findById(long id);

	List<UserEntity> findAll();
	
	UserEntity create(UserDTO obj);
	
	UserEntity update(UserDTO obj);
	
	void delete(Long id);

}
