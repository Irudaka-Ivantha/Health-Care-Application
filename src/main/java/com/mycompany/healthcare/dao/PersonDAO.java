/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.healthcare.dao;

import com.mycompany.healthcare.model.Person;
import com.mycompany.healthcare.exception.ResourceNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Dell
 */



public class PersonDAO {
    private static final Logger w1953903logger = LoggerFactory.getLogger(PersonDAO.class);
    private static List<Person> persons = new ArrayList<>();

    public List<Person> getAllPersons() {
        w1953903logger.info("Fetching all persons...");
        return persons;
    }

    static {
        // Adding some static data for testing
        persons.add(new Person("John Doe", 123456790, "123 Main Street", 1, "2024-03-12", "10:00"));
        persons.add(new Person("Alice Smith", 987654321, "456 Elm Street", 2, "2024-03-12", "11:00"));
        persons.add(new Person("Bob Johnson", 555555555, "789 Oak Avenue", 3, "2024-03-12", "12:00"));
    }

    public Person getPersonById(int id) {
        for (Person person : persons) {
            if (person.getPersonid() == id) {
                w1953903logger.info("Person found with ID: " + id);
                return person;
            }
        }
        w1953903logger.error("Person with ID " + id + " not found");
        throw new ResourceNotFoundException("Person with ID " + id + " not found");
    }

    public void addPerson(Person person) {
        int newPersonId = getNextPersonId();
        person.setPersonid(newPersonId);
        persons.add(person);
        w1953903logger.info("New person added: " + person);
    }

    public void updatePerson(Person updatedPerson) {
        for (int i = 0; i < persons.size(); i++) {
            Person person = persons.get(i);
            if (person.getPersonid() == updatedPerson.getPersonid()) {
                persons.set(i, updatedPerson);
                w1953903logger.info("Person updated: " + updatedPerson);
                return;
            }
        }
        w1953903logger.error("Person with ID " + updatedPerson.getPersonid() + " not found");
        throw new ResourceNotFoundException("Person with ID " + updatedPerson.getPersonid() + " not found");
    }

    public boolean deletePerson(int id) {
        if (!persons.removeIf(person -> person.getPersonid() == id)) {
            w1953903logger.error("Person with ID " + id + " not found");
            throw new ResourceNotFoundException("Person with ID " + id + " not found");
        }
        w1953903logger.info("Person deleted with ID: " + id);
        return true;
    }

    public int getNextPersonId() {
        int maxPersonId = Integer.MIN_VALUE;
        for (Person person : persons) {
            int personId = person.getPersonid();
            if (personId > maxPersonId) {
                maxPersonId = personId;
            }
        }
        return maxPersonId + 1;
    }
}
