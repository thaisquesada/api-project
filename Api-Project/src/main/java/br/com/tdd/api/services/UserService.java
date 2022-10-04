package br.com.tdd.api.services;

import br.com.tdd.api.entity.UserEntity;

public interface UserService {
	
	UserEntity findById(long id);

}
