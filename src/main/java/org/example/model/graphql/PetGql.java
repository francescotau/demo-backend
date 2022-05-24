package org.example.model.graphql;

import lombok.Data;

import java.util.List;

@Data
public class PetGql {
    private int id;
    private String animal;
    private String color;
    private Double weight;
    private List<PersonGql> owners;
}
