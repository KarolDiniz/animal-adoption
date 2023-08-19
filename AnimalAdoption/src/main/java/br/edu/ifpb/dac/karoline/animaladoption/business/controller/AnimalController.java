package br.edu.ifpb.dac.karoline.animaladoption.business.controller;

import br.edu.ifpb.dac.karoline.animaladoption.model.entities.Animal;
import br.edu.ifpb.dac.karoline.animaladoption.business.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AnimalController {
    @Autowired
    private AnimalService animalService;

    public List<Animal> getAllAnimals() {
        return animalService.getAllAnimals();
    }

    public Animal createAnimal(Animal animal) {
        return animalService.createAnimal(animal);
    }

    public Animal updateAnimal(Long id, Animal animal) {
        return animalService.updateAnimal(id, animal);
    }

    public Animal getAnimalById(Long id) {
        return animalService.getAnimalById(id);
    }
    public void deleteAnimal(Long id) {
        animalService.deleteAnimal(id);
    }

}

