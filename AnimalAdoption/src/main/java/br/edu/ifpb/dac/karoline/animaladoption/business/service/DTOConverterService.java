package br.edu.ifpb.dac.karoline.animaladoption.business.service;

import br.edu.ifpb.dac.karoline.animaladoption.model.entities.Animal;
import br.edu.ifpb.dac.karoline.animaladoption.model.entities.User;
import br.edu.ifpb.dac.karoline.animaladoption.business.dto.AnimalDTO;
import br.edu.ifpb.dac.karoline.animaladoption.business.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DTOConverterService {

    public AnimalDTO convertToAnimalDTO(Animal animal) {
        AnimalDTO animalDTO = new AnimalDTO();
        animalDTO.setId(animal.getId());
        animalDTO.setName(animal.getName());
        animalDTO.setSpecies(animal.getSpecies());
        animalDTO.setDescription(animal.getDescription());

        if (animal.getOwner() != null) {
            animalDTO.setOwner(convertToUserDTO(animal.getOwner()));
        }

        return animalDTO;
    }

    public List<AnimalDTO> convertToAnimalDtoList(List<Animal> animals) {
        return animals.stream()
                .map(this::convertToAnimalDTO)
                .collect(Collectors.toList());
    }


    public Animal convertToAnimal(AnimalDTO animalDTO) {
        Animal animal = new Animal();
        animal.setId(animalDTO.getId());
        animal.setName(animalDTO.getName());
        animal.setSpecies(animalDTO.getSpecies());
        animal.setDescription(animalDTO.getDescription());

        if (animalDTO.getOwner() != null) {
            animal.setOwner(convertToUser(animalDTO.getOwner()));
        }

        return animal;
    }

    public UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setAdmin(user.isAdmin());

        return userDTO;
    }

    public User convertToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setAdmin(userDTO.isAdmin());

        return user;
    }
}