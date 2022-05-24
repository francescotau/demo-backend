package org.example.rest;

import io.smallrye.graphql.api.Context;
import org.eclipse.microprofile.graphql.*;
import org.example.business.MainService;
import org.example.model.graphql.PersonGql;
import org.example.model.graphql.PetGql;

import javax.inject.Inject;
import java.util.List;

@GraphQLApi
public class GraphQLService {

    @Inject
    MainService mainService;

    @Inject
    Context context;

    @Query("allPersons")
    @Description("Get all Persons")
    public List<PersonGql> getAllPersons() {
        return mainService.getAllPersons();
    }

    @Query("allPets")
    @Description("Get all Pets")
    public List<PetGql> getAllPets() {
        return mainService.getAllPets();
    }

    @Query("personById")
    @Description("Get Person by Id")
    public PersonGql getFilm(@Name("id") int id) {
        return mainService.getPersonById(id);
    }

    @Mutation("deletePersonById")
    @Description("Delete Person by Id")
    public PersonGql deletePersonById(@Name("id") int id) {
        return mainService.deletePersonById(id);
    }

    @Mutation("addPerson")
    @Description("Add Person")
    public PersonGql addPerson(@Name("id") int id) {
        return mainService.addPerson(id);
    }
}
