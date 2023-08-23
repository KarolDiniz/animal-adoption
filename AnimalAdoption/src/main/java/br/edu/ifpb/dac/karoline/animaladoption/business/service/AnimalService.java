package br.edu.ifpb.dac.karoline.animaladoption.business.service;

import br.edu.ifpb.dac.karoline.animaladoption.model.entities.Animal;

import java.util.List;

public interface AnimalService {

    public Animal createAnimal(Animal animal);

    public List<Animal> getAllAnimals();

    public Animal getAnimalById(Long id);

    public Animal updateAnimal(Long id, Animal updatedAnimal);
    public void deleteAnimal(Long animalId);

}
