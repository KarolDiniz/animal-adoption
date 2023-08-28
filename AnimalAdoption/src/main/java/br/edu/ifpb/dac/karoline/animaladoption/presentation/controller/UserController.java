package br.edu.ifpb.dac.karoline.animaladoption.presentation.controller;

import br.edu.ifpb.dac.karoline.animaladoption.business.service.DTOConverterService;
import br.edu.ifpb.dac.karoline.animaladoption.business.service.UserService;
import br.edu.ifpb.dac.karoline.animaladoption.business.dto.AnimalDTO;
import br.edu.ifpb.dac.karoline.animaladoption.business.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DTOConverterService dtoConverter;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDto) {
        UserDTO createdUserDto = userService.createUser(userDto);

        if (createdUserDto != null) {
            return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDto) {
        UserDTO updatedUserDto = userService.updateUser(id, userDto);

        if (updatedUserDto != null) {
            return new ResponseEntity<>(updatedUserDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);

        if (deleted) {
            String successMessage = "User deleted successfully.";
            return new ResponseEntity<>(successMessage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{id}/animals")
    public ResponseEntity<List<AnimalDTO>> getAnimalsByUserId(@PathVariable Long id) {
        List<AnimalDTO> animals = userService.getAnimalsByUserId(id);

        if (!animals.isEmpty()) {
            return new ResponseEntity<>(animals, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();

        if (!users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
