package br.edu.ifpb.dac.karoline.animaladoption.business.service;

import br.edu.ifpb.dac.karoline.animaladoption.model.entities.Animal;
import br.edu.ifpb.dac.karoline.animaladoption.business.dto.AnimalDTO;

import java.util.List;

public interface AnimalService {

    public AnimalDTO createAnimal(AnimalDTO animal);

    public List<AnimalDTO> getAllAnimals();

    public AnimalDTO getAnimalById(Long id);

    public AnimalDTO updateAnimal(Long id, AnimalDTO updatedAnimal);
    public boolean deleteAnimal(Long animalId);

}
