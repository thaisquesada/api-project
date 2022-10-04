package br.com.tdd.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tdd.api.entity.UserEntity;
import br.com.tdd.api.repository.UserRepository;
import br.com.tdd.api.services.UserService;
import br.com.tdd.api.services.exceptions.ObjectNotFoundException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserEntity findById(long id) {
		Optional<UserEntity> obj = userRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado."));
	}
	
	public List<UserEntity> findAll() {
		return userRepository.findAll();
	}

}