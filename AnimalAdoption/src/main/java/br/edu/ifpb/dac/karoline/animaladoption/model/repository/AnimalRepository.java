package br.edu.ifpb.dac.karoline.animaladoption.model.repository;

import br.edu.ifpb.dac.karoline.animaladoption.model.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findBynameIgnoreCaseContaining(String name);
}

