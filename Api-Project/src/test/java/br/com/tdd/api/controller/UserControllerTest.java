package br.com.tdd.api.controller;

import br.com.tdd.api.dto.UserDTO;
import br.com.tdd.api.entity.UserEntity;
import br.com.tdd.api.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class UserControllerTest {

    public static final Long ID = 1L;
    public static final String NAME = "Jane";
    public static final String EMAIL = "jane@teste.com";
    public static final String PASSWORD = "123456";
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado.";
    public static final String EMAIL_JA_CADASTRADO = "Email já cadastrado no sistema.";
    public static final int INDEX = 0;

    private UserEntity userEntity;
    private UserDTO userDTO;

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void findByIdAndReturnSuccess() {
        when(userService.findById(anyLong())).thenReturn(userEntity);
        when(mapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(PASSWORD, response.getBody().getPassword());
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
    }

}