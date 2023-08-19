package br.edu.ifpb.dac.karoline.animaladoption.business.service;

import br.edu.ifpb.dac.karoline.animaladoption.model.entities.Animal;
import br.edu.ifpb.dac.karoline.animaladoption.model.entities.User;
import br.edu.ifpb.dac.karoline.animaladoption.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());

            return userRepository.save(existingUser);
        } else {
            return null; // Retornar null se o usuário não existir
        }
    }

    public List<Animal> getAnimalsByUserId(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return user.getAnimals();
        } else {
            return Collections.emptyList();
        }
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
