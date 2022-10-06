package br.com.tdd.api.services.impl;

import br.com.tdd.api.dto.UserDTO;
import br.com.tdd.api.entity.UserEntity;
import br.com.tdd.api.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    public static final long ID = 1L;
    public static final String NAME = "Jane";
    public static final String EMAIL = "jane@teste.com";
    public static final String PASSWORD = "123456";

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper mapper;

    private UserEntity userEntity;
    private UserDTO userDTO;
    private Optional<UserEntity> optionalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void findByIdAndReturnAnUserInstance() {
        when(userRepository.findById(anyLong())).thenReturn(optionalUser);

        UserEntity response = userService.findById(ID);

        assertNotNull(response);
        Assertions.assertEquals(UserEntity.class, response.getClass());
        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(NAME, response.getName());
        Assertions.assertEquals(EMAIL, response.getEmail());

    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser() {

        userEntity = new UserEntity(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new UserEntity(ID, NAME, EMAIL, PASSWORD));
    }

}