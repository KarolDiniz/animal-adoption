package br.edu.ifpb.dac.karoline.animaladoption.business.service.impl;

import br.edu.ifpb.dac.karoline.animaladoption.business.dto.AnimalDTO;
import br.edu.ifpb.dac.karoline.animaladoption.business.dto.UserDTO;
import br.edu.ifpb.dac.karoline.animaladoption.business.exception.UserHasAnimalsException;
import br.edu.ifpb.dac.karoline.animaladoption.business.service.DTOConverterService;
import br.edu.ifpb.dac.karoline.animaladoption.business.service.UserService;
import br.edu.ifpb.dac.karoline.animaladoption.model.entities.Animal;
import br.edu.ifpb.dac.karoline.animaladoption.model.entities.User;
import br.edu.ifpb.dac.karoline.animaladoption.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DTOConverterService dtoConverter;

    @Override
    public UserDTO createUser(UserDTO userDto) {
        User user = dtoConverter.convertToUser(userDto);
        user = userRepository.save(user);
        UserDTO createdUserDto = dtoConverter.convertToUserDTO(user);
        return createdUserDto;
    }

    @Override
    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return dtoConverter.convertToUserDTO(user);
        } else {
            return null;
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(dtoConverter::convertToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDto) {
        User existingUser = userRepository.findById(id).orElse(null);

        if (existingUser != null) {
            existingUser.setUsername(userDto.getUsername());
            existingUser.setAdmin(userDto.isAdmin());

            User updatedUser = userRepository.save(existingUser);

            return dtoConverter.convertToUserDTO(updatedUser);
        } else {
            return null;
        }
    }

    @Override
    public List<AnimalDTO> getAnimalsByUserId(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            List<Animal> animals = user.getAnimals();

            return animals.stream()
                    .map(dtoConverter::convertToAnimalDTO)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            if (!user.getAnimals().isEmpty()) {
                throw new UserHasAnimalsException("The user is associated with one or more animals and cannot be deleted.");
            }

            userRepository.deleteById(userId);
        }
    }
}