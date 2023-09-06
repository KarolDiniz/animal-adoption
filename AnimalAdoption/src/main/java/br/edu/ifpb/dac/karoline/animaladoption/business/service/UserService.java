package br.edu.ifpb.dac.karoline.animaladoption.business.service;

import br.edu.ifpb.dac.karoline.animaladoption.business.dto.AnimalDTO;
import br.edu.ifpb.dac.karoline.animaladoption.business.dto.UserDTO;

import java.util.List;

public interface UserService {


    public UserDTO getById(Long userId);
    public UserDTO create(UserDTO user);

    public List<UserDTO> getAll();

    public UserDTO update(Long id, UserDTO user);

    public List<AnimalDTO> getAnimalsByUserId(Long userId);

    public boolean delete(Long userId);

}
