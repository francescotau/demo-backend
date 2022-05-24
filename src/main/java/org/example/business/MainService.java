package org.example.business;

import org.example.exception.CustomGraphQLException;
import org.example.model.entity.EventActivityEntity;
import org.example.model.entity.EventEntity;
import org.example.model.graphql.PersonGql;
import org.example.model.graphql.PetGql;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Transactional
public class MainService {

    final List<PersonGql> persons = new ArrayList<>();
    final List<PetGql> pets = new ArrayList<>();

    public MainService() {
        // Persons

        PersonGql p1 = new PersonGql();
        p1.setId(1);
        p1.setName("name1");
        p1.setSurname("surname1");
        p1.setAge(19);

        PersonGql p2 = new PersonGql();
        p2.setId(2);
        p2.setName("name2");
        p2.setSurname("surname2");
        p2.setAge(44);

        PersonGql p3 = new PersonGql();
        p3.setId(3);
        p3.setName("name3");
        p3.setSurname("surname3");
        p3.setAge(23);

        persons.add(p1);
        persons.add(p2);
        persons.add(p3);

        // Pets
        PetGql pe1 = new PetGql();
        pe1.setId(1);
        pe1.setAnimal("dog");
        pe1.setColor("black");
        pe1.setWeight(10.0);
        List<PersonGql> owners1 = new ArrayList<>();
        owners1.add(p1);
        pe1.setOwners(owners1);

        PetGql pe2 = new PetGql();
        pe2.setId(2);
        pe2.setAnimal("cat");
        pe2.setColor("red");
        pe2.setWeight(3.0);
        List<PersonGql> owners2 = new ArrayList<>();
        owners2.add(p2);
        pe2.setOwners(owners2);

        PetGql pe3 = new PetGql();
        pe3.setId(3);
        pe3.setAnimal("turtle");
        pe3.setColor("green");
        pe3.setWeight(0.3);
        List<PersonGql> owners3 = new ArrayList<>();
        owners3.add(p1);
        owners3.add(p3);
        pe3.setOwners(owners3);

        pets.add(pe1);
        pets.add(pe2);
        pets.add(pe3);
    }

    @Inject
    EntityManager entityManager;

    public EventEntity test(String owner) {
        System.out.println("Hello, " + owner);

        EventEntity eventEntity = new EventEntity();
        eventEntity.setOwner(owner);
        EventEntity result = entityManager.merge(eventEntity);

        EventActivityEntity eventActivityEntity = new EventActivityEntity();
        eventActivityEntity.setStartTime("09:00");
        result.addActivity(eventActivityEntity);

        EventEntity result2 = entityManager.merge(result);
        result2.setOwner(owner + "asd");
        return result2;
    }

    /*
    GrapQL methods
     */

    public List<PersonGql> getAllPersons() {
        throw new CustomGraphQLException("getAllPersons error");
        // return persons;
    }

    public List<PetGql> getAllPets() {
        return pets;
    }

    public PersonGql getPersonById(int id) {
        return persons.stream().filter(personGql -> personGql.getId() == id).findFirst().orElseThrow(() -> new CustomGraphQLException("getPersonById not found for id: " + id));
    }

    public PersonGql addPerson(int id) {
        PersonGql personGql = new PersonGql();
        personGql.setId(id);
        personGql.setName(id + "name");
        personGql.setSurname(id + "surname");
        personGql.setAge(id + 10);

        persons.add(personGql);
        return personGql;
    }


    public PersonGql deletePersonById(int id) {
        PersonGql toRemove = getPersonById(id);
        persons.removeIf(personGql -> personGql.getId() == id);

        return toRemove;
    }
}
