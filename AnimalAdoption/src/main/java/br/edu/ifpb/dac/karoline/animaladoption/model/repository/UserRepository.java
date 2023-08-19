package br.edu.ifpb.dac.karoline.animaladoption.model.repository;

import br.edu.ifpb.dac.karoline.animaladoption.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
