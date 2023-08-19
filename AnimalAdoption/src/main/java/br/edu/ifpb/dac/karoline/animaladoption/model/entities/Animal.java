package br.edu.ifpb.dac.karoline.animaladoption.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "species")
    private String species;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

}
