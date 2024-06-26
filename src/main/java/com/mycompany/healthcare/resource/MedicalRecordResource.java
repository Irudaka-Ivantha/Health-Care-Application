package com.mycompany.healthcare.resource;

import com.mycompany.healthcare.dao.MedicalRecordDAO;
import com.mycompany.healthcare.model.MedicalRecord;
import com.mycompany.healthcare.model.Patient;
import com.mycompany.healthcare.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/medicalrecord")
public class MedicalRecordResource {
    private static final Logger w1953903logger = LoggerFactory.getLogger(MedicalRecordResource.class);
    private MedicalRecordDAO medicalRecordDAO = new MedicalRecordDAO();

    @GET
    @Path("/getall")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMedicalRecords() {
        try {
            List<MedicalRecord> medicalRecords = medicalRecordDAO.getAllMedicalRecords();
            return Response.ok(medicalRecords).build();
        } catch (Exception e) {
            w1953903logger.error("Failed to fetch all medical records: " + e.getMessage(), e);
            throw new ResourceNotFoundException("Failed to fetch all medical records: " + e.getMessage());
        }
    }

    @GET
    @Path("/{medicalrecordId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMedicalRecordById(@PathParam("medicalrecordId") int medicalrecordId) {
        try {
            MedicalRecord medicalRecord = medicalRecordDAO.getMediaRecordById(medicalrecordId);
            return Response.ok(medicalRecord).build();
        } catch (Exception e) {
            w1953903logger.error("Failed to fetch medical record with ID " + medicalrecordId + ": " + e.getMessage(), e);
            throw new ResourceNotFoundException("Failed to fetch medical record with ID " + medicalrecordId + ": " + e.getMessage());
        }
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMedicalRecord(MedicalRecord medicalRecord) {
        try {
            medicalRecordDAO.addMedicalRecord(medicalRecord);
            w1953903logger.info("Medical record added successfully.");
            return Response.status(Response.Status.CREATED)
                           .entity("{\"message\": \"Medical record added successfully.\"}")
                           .build();
        } catch (Exception e) {
            w1953903logger.error("Failed to add medical record: " + e.getMessage(), e);
            throw new ResourceNotFoundException("Failed to add medical record: " + e.getMessage());
        }
    }

    @PUT
    @Path("/{medicalrecordId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMedicalRecord(@PathParam("medicalrecordId") int medicalrecordId, MedicalRecord updatedMedicalRecord) {
        try {
            MedicalRecord existingMedicalRecord = medicalRecordDAO.getMediaRecordById(medicalrecordId);
            if (existingMedicalRecord != null) {
                updatedMedicalRecord.setMedicalRecordid(medicalrecordId);
                medicalRecordDAO.updateMedicalRecord(updatedMedicalRecord);
                w1953903logger.info("Medical record updated: " + medicalrecordId);
                return Response.ok().entity("{\"message\": \"Medical record updated successfully.\"}").build();
            } else {
                w1953903logger.warn("Medical record not found for updating: " + medicalrecordId);
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("{\"error\": \"Medical record not found.\"}")
                               .build();
            }
        } catch (Exception e) {
            w1953903logger.error("Failed to update medical record: " + e.getMessage(), e);
            throw new ResourceNotFoundException("Failed to update medical record: " + e.getMessage());
        }
    }

    @DELETE
    @Path("/{medicalrecordId}")
    public Response deleteMedicalRecord(@PathParam("medicalrecordId") int medicalrecordId) {
        try {
            boolean deleted = medicalRecordDAO.deleteMedicalRecord(medicalrecordId);
            if (deleted) {
                w1953903logger.info("Medical record deleted successfully with ID: " + medicalrecordId);
                return Response.ok().entity("{\"message\": \"Medical record deleted successfully.\"}").build();
            } else {
                w1953903logger.warn("Medical record not found for deletion: " + medicalrecordId);
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("{\"error\": \"Medical record not found.\"}")
                               .build();
            }
        } catch (Exception e) {
            w1953903logger.error("Failed to delete medical record with ID " + medicalrecordId + ": " + e.getMessage(), e);
            throw new ResourceNotFoundException("Failed to delete medical record with ID " + medicalrecordId + ": " + e.getMessage());
        }
    }

    @GET
    @Path("/{medicalrecordId}/patient")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPatientForMedicalRecord(@PathParam("medicalrecordId") int medicalrecordId) {
        try {
            MedicalRecord medicalRecord = medicalRecordDAO.getMediaRecordById(medicalrecordId);
            if (medicalRecord != null) {
                Patient patient = medicalRecord.getPatientDetails();
                if (patient != null) {
                    return Response.ok(patient).build();
                } else {
                    return Response.status(Response.Status.NOT_FOUND)
                                   .entity("{\"error\": \"Patient not found for the medical record.\"}")
                                   .build();
                }
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("{\"error\": \"Medical record not found.\"}")
                               .build();
            }
        } catch (Exception e) {
            w1953903logger.error("Failed to fetch patient for medical record with ID " + medicalrecordId + ": " + e.getMessage(), e);
            throw new ResourceNotFoundException("Failed to fetch patient for medical record with ID " + medicalrecordId + ": " + e.getMessage());
        }
    }

}