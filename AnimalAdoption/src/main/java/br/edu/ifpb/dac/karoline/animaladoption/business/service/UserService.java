package br.edu.ifpb.dac.karoline.animaladoption.business.service;

import br.edu.ifpb.dac.karoline.animaladoption.business.dto.AnimalDTO;
import br.edu.ifpb.dac.karoline.animaladoption.business.dto.UserDTO;
import br.edu.ifpb.dac.karoline.animaladoption.model.entities.Animal;
import br.edu.ifpb.dac.karoline.animaladoption.model.entities.User;

import java.util.Collections;
import java.util.List;

public interface UserService {


    public UserDTO getUserById(Long userId);
    public UserDTO createUser(UserDTO user);

    public List<UserDTO> getAllUsers();
    public User findByUsername(String username);

    public UserDTO updateUser(Long id, UserDTO user);

    public List<AnimalDTO> getAnimalsByUserId(Long userId);

    public void deleteUser(Long userId);

}
