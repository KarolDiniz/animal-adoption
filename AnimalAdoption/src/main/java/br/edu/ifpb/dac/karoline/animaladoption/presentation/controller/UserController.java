package br.edu.ifpb.dac.karoline.animaladoption.presentation.controller;

import br.edu.ifpb.dac.karoline.animaladoption.business.service.UserService;
import br.edu.ifpb.dac.karoline.animaladoption.business.service.impl.UserServiceImpl;
import br.edu.ifpb.dac.karoline.animaladoption.model.entities.Animal;
import br.edu.ifpb.dac.karoline.animaladoption.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public User createUser(User user) {
        return userService.createUser(user);
    }

    public User updateUser(Long id, User user) {
        return userService.updateUser(id, user);
    }

    public User findByUsername(String username) {
        return userService.findByUsername(username);
    }

    public List<Animal> getUserAnimals(Long userId) {
        return userService.getAnimalsByUserId(userId);
    }
    public void deleteUser(Long id) {
        userService.deleteUser(id);
    }

}

