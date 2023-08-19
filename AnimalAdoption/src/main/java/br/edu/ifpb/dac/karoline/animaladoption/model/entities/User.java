package br.edu.ifpb.dac.karoline.animaladoption.model.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "isAdmin")
    private boolean isAdmin;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<Animal> animals = new ArrayList<>();

}