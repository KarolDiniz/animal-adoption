package br.edu.ifpb.dac.karoline.animaladoption.presentation.controller;

import br.edu.ifpb.dac.karoline.animaladoption.business.service.AnimalService;

import br.edu.ifpb.dac.karoline.animaladoption.business.dto.AnimalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animals")
@CrossOrigin(origins = "http://localhost:3000") // URL do seu aplicativo React em desenvolvimento
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @PostMapping
    public ResponseEntity<String> createAnimal(@RequestBody AnimalDTO animalDTO) {
        AnimalDTO createdAnimalDTO = animalService.create(animalDTO);
        System.out.println("Animal " + createdAnimalDTO.getName());
        String successMessage = "Animal " + createdAnimalDTO.getName() + " has been successfully bred..";
        return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<AnimalDTO>> getAllAnimals() {
        List<AnimalDTO> animals = animalService.getAll();
        return new ResponseEntity<>(animals, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AnimalDTO> getAnimalById(@PathVariable Long id) {
        AnimalDTO animalDTO = animalService.getById(id);
        if (animalDTO != null) {
            return new ResponseEntity<>(animalDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalDTO> updateAnimal(@PathVariable Long id, @RequestBody AnimalDTO animalDTO) {
        AnimalDTO updatedAnimalDTO = animalService.update(id, animalDTO);

        if (updatedAnimalDTO != null) {
            return new ResponseEntity<>(updatedAnimalDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAnimal(@PathVariable Long id) {
        boolean deleted = animalService.delete(id);
        if (deleted) {
            String successMessage = "Animal deleted successfully.";
            return new ResponseEntity<>(successMessage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Animal not found.", HttpStatus.NOT_FOUND);
        }
    }
}
