package org.example.model.graphql;

import lombok.Data;

@Data
public class PersonGql {
    private int id;
    private String name;
    private String surname;
    private int age;
}