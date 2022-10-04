package br.com.tdd.api.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.tdd.api.entity.UserEntity;
import br.com.tdd.api.repository.UserRepository;

@Configuration
@Profile("local")
public class LocalConfig {

	@Autowired
	private UserRepository userRepository;
	
	@Bean
	public void startDB() {
		UserEntity u1 = new UserEntity(1, "Mary", "mary@email.com", "123");
		UserEntity u2 = new UserEntity(2, "John", "john@email.com", "456");
		
		userRepository.saveAll(List.of(u1, u2));
	}
	
}
