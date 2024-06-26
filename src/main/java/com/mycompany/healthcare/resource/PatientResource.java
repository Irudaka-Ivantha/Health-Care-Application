/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.healthcare.resource;




/**
 *
 * @author Dell
 */
import com.mycompany.healthcare.dao.MedicalRecordDAO;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import com.mycompany.healthcare.dao.PatientDAO;
import com.mycompany.healthcare.model.MedicalRecord;
import com.mycompany.healthcare.model.Patient;
import com.mycompany.healthcare.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/patient")
public class PatientResource {
    private static final Logger w1953903logger = LoggerFactory.getLogger(PatientResource.class);
    private PatientDAO patientDAO = new PatientDAO();
    private MedicalRecordDAO medicalrecordDAO=new MedicalRecordDAO();

    @GET
    @Path("/getall")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPatients() {
        try {
            List<Patient> patients = patientDAO.getAllPatients();
            w1953903logger.info("All patients fetched.");
            return Response.ok().entity(patients).build();
        } catch (Exception e) {
            w1953903logger.error("Failed to fetch patients.", e);
            throw new ResourceNotFoundException("Failed to fetch patients.");
        }
    }

    @GET
    @Path("/{patientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPatientById(@PathParam("patientId") int patientId) {
        try {
            Patient patient = patientDAO.getPatientById(patientId);
            w1953903logger.info("Patient found with ID: " + patientId);
            return Response.ok().entity(patient).build();
        } catch (ResourceNotFoundException e) {
            w1953903logger.error("Patient not found with ID: " + patientId);
            throw e;
        } catch (Exception e) {
            w1953903logger.error("Failed to fetch patient with ID: " + patientId, e);
            throw new ResourceNotFoundException("Failed to fetch patient with ID: " + patientId);
        }
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPatient(Patient patient) {
        try {
            patientDAO.addPatient(patient);
            w1953903logger.info("New patient added: " + patient);
            return Response.status(Response.Status.CREATED).entity(patient).build();
        } catch (Exception e) {
            w1953903logger.error("Failed to add patient.", e);
            throw new ResourceNotFoundException("Failed to add patient.");
        }
    }

    @PUT
@Path("/{patientId}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response updatePatient(@PathParam("patientId") int patientId, Patient updatedPatient) {
    try {
        Patient existingPatient = patientDAO.getPatientById(patientId);
        if (existingPatient != null) {
            updatedPatient.setPatientid(patientId);
            patientDAO.updatePatient(updatedPatient);
            w1953903logger.info("Patient updated with ID: " + patientId);
            return Response.ok(updatedPatient).build(); // Return the updated patient in the response
        } else {
            w1953903logger.error("Patient not found with ID: " + patientId);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    } catch (Exception e) {
        w1953903logger.error("Failed to update patient with ID: " + patientId, e);
        throw new ResourceNotFoundException("Failed to update patient with ID: " + patientId);
    }
}

@DELETE
@Path("/{patientId}")
@Produces(MediaType.APPLICATION_JSON)
public Response deletePatient(@PathParam("patientId") int patientId) {
    try {
        boolean deleted = patientDAO.deletePatient(patientId);
        if (deleted) {
            w1953903logger.info("Patient deleted successfully with ID: " + patientId);
            // Return a message indicating successful deletion
            return Response.ok("{\"message\": \"Patient with ID " + patientId + " deleted successfully.\"}").build();
        } else {
            w1953903logger.warn("Patient not found for deletion: " + patientId);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    } catch (Exception e) {
        w1953903logger.error("Failed to delete patient with ID " + patientId + ": " + e.getMessage(), e);
        throw new ResourceNotFoundException("Failed to delete patient with ID " + patientId + ": " + e.getMessage());
    }
}

@GET
@Path("/{medicalrecordId}/patient")
@Produces(MediaType.APPLICATION_JSON)
public Response getMedicalRecordForPatient(@PathParam("medicalrecordId") int medicalrecordId) {
    w1953903logger.info("Fetching medical record for ID: {}", medicalrecordId);

    MedicalRecord medicalrecord = medicalrecordDAO.getMediaRecordById(medicalrecordId);

    if (medicalrecord != null) {
        Patient patient = medicalrecord.getPatientDetails();

        if (patient != null) {
            w1953903logger.info("Patient found for medical record ID: {}", medicalrecordId);
            // Return the patient data and medical record data in the response
            return Response.ok("{\"Medical record\": {\"id\": " + medicalrecordId + ", \"patient\": " + patient + "}}").build();
        } else {
            w1953903logger.error("Patient not found for medical record ID: {}", medicalrecordId);
            throw new ResourceNotFoundException("Patient not found for the medical record.");
        }
    } else {
        w1953903logger.error("Medical record not found for ID: {}", medicalrecordId);
        throw new ResourceNotFoundException("Medical record not found.");
    }
}

}

    
    


