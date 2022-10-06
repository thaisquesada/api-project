package br.com.tdd.api.services.impl;

import br.com.tdd.api.dto.UserDTO;
import br.com.tdd.api.entity.UserEntity;
import br.com.tdd.api.repository.UserRepository;
import br.com.tdd.api.services.exceptions.DataIntegrityViolationException;
import br.com.tdd.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    public static final Long ID = 1L;
    public static final String NAME = "Jane";
    public static final String EMAIL = "jane@teste.com";
    public static final String PASSWORD = "123456";
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado.";
    public static final int INDEX = 0;

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
    void findByIdAndReturnObjectNotFoundException() {
        when(userRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));

        try {
            userService.findById(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
        }
    }

    @Test
    void findAllAndReturnAListOfUsers() {
        when(userRepository.findAll()).thenReturn(List.of(userEntity));

        List<UserEntity> response = userService.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(UserEntity.class, response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(ID, response.get(INDEX).getName());
        assertEquals(ID, response.get(INDEX).getEmail());
        assertEquals(ID, response.get(INDEX).getPassword());

    }

    @Test
    void createAndReturnSuccess() {
        when(userRepository.save(any())).thenReturn(userEntity);

        UserEntity response = userService.create(userDTO);

        assertNotNull(response);
        assertEquals(UserEntity.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void createAndReturnDataIntegrityViolationException() {
        when(userRepository.findByEmail(anyString())).thenReturn(optionalUser);

        try {
            optionalUser.get().setId(2L);
            userService.create(userDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals("Email já cadastrado no sistema.", ex.getMessage());
        }

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