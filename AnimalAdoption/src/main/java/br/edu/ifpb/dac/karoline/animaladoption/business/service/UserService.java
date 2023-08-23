package br.edu.ifpb.dac.karoline.animaladoption.business.service;

import br.edu.ifpb.dac.karoline.animaladoption.model.entities.Animal;
import br.edu.ifpb.dac.karoline.animaladoption.model.entities.User;

import java.util.Collections;
import java.util.List;

public interface UserService {


    public User createUser(User user);

    public List<User> getAllUsers();
    public User findByUsername(String username);

    public User updateUser(Long id, User user);

    public List<Animal> getAnimalsByUserId(Long userId);

    public void deleteUser(Long userId);

}
