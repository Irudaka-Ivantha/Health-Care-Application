/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.healthcare.resource;
import com.mycompany.healthcare.dao.PrescriptionDAO;
import com.mycompany.healthcare.model.Prescription;
import com.mycompany.healthcare.exception.ResourceNotFoundException;
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

/**
 *
 * @author Dell
 */
@Path("/prescription")
public class PrescriptionResource {
    private static final Logger w953903logger = LoggerFactory.getLogger(PrescriptionResource.class);
    private PrescriptionDAO prescriptionDAO = new PrescriptionDAO();

    @GET
    @Path("/getall")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPrescriptions() {
        try {
            List<Prescription> prescriptions = prescriptionDAO.getAllPrescriptions();
            w953903logger.info("All prescriptions fetched.");
            return Response.ok(prescriptions).build();
        } catch (Exception e) {
            w953903logger.error("Failed to retrieve all prescriptions: " + e.getMessage(), e);
            throw new ResourceNotFoundException("Failed to retrieve all prescriptions: " + e.getMessage());
        }
    }

    @GET
    @Path("/{prescriptionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPrescriptionById(@PathParam("prescriptionId") int prescriptionId) {
        try {
            Prescription prescription = prescriptionDAO.getPrescriptionById(prescriptionId);
            if (prescription != null) {
                w953903logger.info("Prescription fetched: " + prescriptionId);
                return Response.ok(prescription).build();
            } else {
                w953903logger.warn("Prescription not found: " + prescriptionId);
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            w953903logger.error("Failed to retrieve prescription: " + e.getMessage(), e);
            throw new ResourceNotFoundException("Failed to retrieve prescription: " + e.getMessage());
        }
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPrescription(Prescription prescription) {
        try {
            prescriptionDAO.addPrescription(prescription);
            w953903logger.info("New prescription added.");
            return Response.status(Response.Status.CREATED).entity("{\"message\": \"Prescription added successfully.\"}").build();
        } catch (Exception e) {
            w953903logger.error("Failed to add prescription: " + e.getMessage(), e);
            throw new ResourceNotFoundException("Failed to add prescription: " + e.getMessage());
        }
    }

    @PUT
    @Path("/{prescriptionId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePrescription(@PathParam("prescriptionId") int prescriptionId, Prescription updatedPrescription) {
        try {
            Prescription existingPrescription = prescriptionDAO.getPrescriptionById(prescriptionId);
            if (existingPrescription != null) {
                updatedPrescription.setPrescriptionid(prescriptionId);
                prescriptionDAO.updatePrescription(updatedPrescription);
                w953903logger.info("Prescription updated: " + prescriptionId);
                return Response.ok(updatedPrescription).entity("{\"message\": \"Prescription updated successfully.\"}").build();
            } else {
                w953903logger.warn("Prescription not found for updating: " + prescriptionId);
                return Response.status(Response.Status.NOT_FOUND).entity("{\"error\": \"Prescription not found.\"}").build();
            }
        } catch (Exception e) {
            w953903logger.error("Failed to update prescription: " + e.getMessage(), e);
            throw new ResourceNotFoundException("Failed to update prescription: " + e.getMessage());
        }
    }


    @DELETE
    @Path("/{prescriptionId}")
    public Response deletePrescription(@PathParam("prescriptionId") int prescriptionId) {
        try {
            boolean deleted = prescriptionDAO.deletePrescription(prescriptionId);
            if (deleted) {
                w953903logger.info("Prescription deleted: " + prescriptionId);
                return Response.noContent().entity("{\"message\": \"Prescription deleted successfully.\"}").build();
            } else {
                w953903logger.warn("Prescription not found for deletion: " + prescriptionId);
                return Response.status(Response.Status.NOT_FOUND).entity("{\"error\": \"Prescription not found.\"}").build();
            }
        } catch (Exception e) {
            w953903logger.error("Failed to delete prescription: " + e.getMessage(), e);
            throw new ResourceNotFoundException("Failed to delete prescription: " + e.getMessage());
        }
}


}