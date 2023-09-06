package br.edu.ifpb.dac.karoline.animaladoption.business.service.impl;

import br.edu.ifpb.dac.karoline.animaladoption.business.dto.UserDTO;
import br.edu.ifpb.dac.karoline.animaladoption.business.exception.UserNotFoundException;
import br.edu.ifpb.dac.karoline.animaladoption.business.service.UserService;
import br.edu.ifpb.dac.karoline.animaladoption.model.entities.User;
import br.edu.ifpb.dac.karoline.animaladoption.model.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateUser() {
        UserDTO userDto = new UserDTO();
        userDto.setUsername("Tiago_Argentino");

        UserDTO createdUserDto = userService.create(userDto);

        assertNotNull(createdUserDto);  //não é null.
        assertNotNull(createdUserDto.getId());
        assertEquals("Tiago_Argentino", createdUserDto.getUsername()); //são iguais.
    }

    @Test
    public void testDeleteUserById() {
        User user = new User();
        user.setId(1L);
        user.setUsername("Tiago_Brasileiro");
        userRepository.save(user);

        userService.delete(1L);

        Optional<User> deletedUser = userRepository.findById(1L);
        assertFalse(deletedUser.isPresent());
    }

    @Test
    public void testDeleteUserByIdUserNotFound() {
        assertThrows(UserNotFoundException.class, () -> userService.delete(999L));
    }

}

