package br.edu.ifpb.dac.karoline.animaladoption.business.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnimalDTO {

    private Long id;
    private String name;
    private String species;
    private String description;
    private UserDTO owner;

}