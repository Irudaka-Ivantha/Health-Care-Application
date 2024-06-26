/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.healthcare.resource;

import com.mycompany.healthcare.dao.BillingDAO;
import com.mycompany.healthcare.model.Billing;
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
@Path("/billing")
public class BillingResource {
    private static final Logger w1953903logger = LoggerFactory.getLogger(BillingResource.class);
    private BillingDAO billingDAO = new BillingDAO();

    @GET
    @Path("/getall")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBillings() {
        try {
            List<Billing> billings = billingDAO.getAllBillings();
            w1953903logger.info("All billings fetched..");
            return Response.ok().entity(billings).build();
        } catch (Exception e) {
            w1953903logger.error("Failed to retrieve all billings: " + e.getMessage(), e);
            throw new ResourceNotFoundException("Failed to retrieve all billings: " + e.getMessage());
        }
    }

    @GET
    @Path("/{billingId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBillingById(@PathParam("billingId") int billingId) {
        try {
            Billing billing = billingDAO.getBillingById(billingId);
            if (billing != null) {
                w1953903logger.info("Billing fetched: " + billingId);
                return Response.ok().entity(billing).build();
            } else {
                w1953903logger.warn("Billing not found: " + billingId);
                return Response.status(Response.Status.NOT_FOUND).entity("Billing not found with ID: " + billingId).build();
            }
        } catch (Exception e) {
            w1953903logger.error("Failed to retrieve billing: " + e.getMessage(), e);
            throw new ResourceNotFoundException("Failed to retrieve billing: " + e.getMessage());
        }
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBilling(Billing billing) {
        try {
            billingDAO.addBilling(billing);
            w1953903logger.info("New billing added..");
            return Response.status(Response.Status.CREATED).entity("Billing added successfully").build();
        } catch (Exception e) {
            w1953903logger.error("Failed to add billing: " + e.getMessage(), e);
            throw new ResourceNotFoundException("Failed to add billing: " + e.getMessage());
        }
    }

    @PUT
    @Path("/{billingId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBilling(@PathParam("billingId") int billingId, Billing updatedBilling) {
        try {
            Billing existingBilling = billingDAO.getBillingById(billingId);
            if (existingBilling != null) {
                updatedBilling.setBillingid(billingId);
                billingDAO.updateBilling(updatedBilling);
                w1953903logger.info("Billing updated: " + billingId);
                return Response.ok().entity("Billing updated successfully").build();
            } else {
                w1953903logger.warn("Billing not found for updating: " + billingId);
                return Response.status(Response.Status.NOT_FOUND).entity("Billing not found with ID: " + billingId).build();
            }
        } catch (Exception e) {
            w1953903logger.error("Failed to update billing: " + e.getMessage(), e);
            throw new ResourceNotFoundException("Failed to update billing: " + e.getMessage());
        }
    }

    @DELETE
@Path("/{billingId}")
public Response deleteBilling(@PathParam("billingId") int billingId) {
    try {
        boolean deleted = billingDAO.deleteBilling(billingId);
        if (deleted) {
            w1953903logger.info("Billing deleted: " + billingId);
            // Return a message indicating successful deletion
            return Response.ok("{\"message\": \"Billing with ID " + billingId + " deleted successfully.\"}").build();
        } else {
            w1953903logger.warn("Billing not found for deletion: " + billingId);
            throw new ResourceNotFoundException("Billing with ID " + billingId + " not found");
        }
    } catch (Exception e) {
        w1953903logger.error("Failed to delete billing: " + e.getMessage(), e);
        throw new ResourceNotFoundException("Failed to delete billing: " + e.getMessage());
    }
}
}
