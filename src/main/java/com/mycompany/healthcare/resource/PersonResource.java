package com.mycompany.healthcare.resource;

import com.mycompany.healthcare.dao.PersonDAO;
import com.mycompany.healthcare.model.Person;
import com.mycompany.healthcare.exception.ResourceNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/person")
public class PersonResource {
    private static final Logger w1953903logger = LoggerFactory.getLogger(PersonResource.class);
    private PersonDAO personDAO = new PersonDAO();
    
    @GET
    @Path("/getall")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersons() {
        try {
            List<Person> persons = personDAO.getAllPersons();
            w1953903logger.info("All persons fetched..");
            return Response.ok(persons).build();
        } catch (Exception e) {
            w1953903logger.error("Failed to retrieve persons: " + e.getMessage(), e);
            throw new ResourceNotFoundException("Failed to retrieve persons: " + e.getMessage());
        }
    }

    @GET
    @Path("/{personId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonById(@PathParam("personId") int personId) {
        try {
            Person person = personDAO.getPersonById(personId);
            if (person != null) {
                w1953903logger.info("Person fetched: " + personId);
                return Response.ok(person).build();
            } else {
                w1953903logger.warn("Person not found : " + personId);
                throw new ResourceNotFoundException("Person with ID " + personId + " not found");
            }
        } catch (Exception e) {
            w1953903logger.error("Failed to retrieve person: " + e.getMessage(), e);
            throw new ResourceNotFoundException("Failed to retrieve person: " + e.getMessage());
        }
    }
    
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPerson(Person person) {
        try {
            String currentDate = getCurrentDate();
            String currentTime = getCurrentTime();

            person.setDate(currentDate);
            person.setTime(currentTime);

            personDAO.addPerson(person);
            w1953903logger.info("New person added..");
            return Response.status(Response.Status.CREATED).entity(person).build();
        } catch (Exception e) {
            w1953903logger.error("Failed to add person: " + e.getMessage(), e);
            throw new ResourceNotFoundException("Failed to add person: " + e.getMessage());
        }
    }

    @PUT
@Path("/{personId}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response updatePerson(@PathParam("personId") int personId, Person updatedPerson) {
    try {
        Person existingPerson = personDAO.getPersonById(personId);
        if (existingPerson != null) {
            updatedPerson.setPersonid(personId);
            personDAO.updatePerson(updatedPerson);
            w1953903logger.info("Person updated: " + personId);
            return Response.ok(updatedPerson).build(); // Return the updated person in the response
        } else {
            w1953903logger.warn("Person not found for updating: " + personId);
            throw new ResourceNotFoundException("Person with ID " + personId + " not found");
        }
    } catch (Exception e) {
        w1953903logger.error("Failed to update person: " + e.getMessage(), e);
        throw new ResourceNotFoundException("Failed to update person: " + e.getMessage());
    }
}

@DELETE
@Path("/{personId}")
@Produces(MediaType.APPLICATION_JSON)
public Response deletePerson(@PathParam("personId") int personId) {
    try {
        boolean deleted = personDAO.deletePerson(personId);
        if (deleted) {
            w1953903logger.info("Person deleted: " + personId);
            // Return a message indicating successful deletion
            return Response.ok("{\"message\": \"Person with ID " + personId + " deleted successfully.\"}").build();
        } else {
            w1953903logger.warn("Person not found for deletion: " + personId);
            throw new ResourceNotFoundException("Person with ID " + personId + " not found");
        }
    } catch (Exception e) {
        w1953903logger.error("Failed to delete person: " + e.getMessage(), e);
        throw new ResourceNotFoundException("Failed to delete person: " + e.getMessage());
    }
}

    private String getCurrentDate() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
    
    private String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
