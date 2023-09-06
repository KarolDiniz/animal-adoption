package br.edu.ifpb.dac.karoline.animaladoption.business.service;

import br.edu.ifpb.dac.karoline.animaladoption.business.dto.AnimalDTO;

import java.util.List;

public interface AnimalService {

    public AnimalDTO create(AnimalDTO animal);

    public List<AnimalDTO> getAll();

    public AnimalDTO getById(Long id);

    public AnimalDTO update(Long id, AnimalDTO updatedAnimal);
    public boolean delete(Long animalId);

}
