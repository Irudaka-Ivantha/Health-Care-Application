package com.mycompany.healthcare.resource;

import com.mycompany.healthcare.dao.AppointmentDAO;
import com.mycompany.healthcare.model.Appointment;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.mycompany.healthcare.exception.ResourceNotFoundException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/appointment")
public class AppointmentResource {
    private static final Logger w1953903logger = LoggerFactory.getLogger(AppointmentResource.class);
    private AppointmentDAO appointmentDAO = new AppointmentDAO();

    @GET
    @Path("/getall")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Appointment> getAllAppointments() {
        try {
            List<Appointment> appointments = appointmentDAO.getAllAppointments();
            w1953903logger.info("All appointments fetched..");
            return appointments;
        } catch (Exception e) {
            w1953903logger.error("Failed to retrieve all appointments: " + e.getMessage(), e);
            throw new ResourceNotFoundException("Failed to retrieve all appointments: " + e.getMessage());
        }
    }

    @GET
    @Path("/{appointmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAppointmentById(@PathParam("appointmentId") int appointmentId) {
        try {
            Appointment appointment = appointmentDAO.getAppointmentById(appointmentId);
            w1953903logger.info("Appointment fetched: " + appointmentId);
            return Response.ok(appointment).build();
        } catch (ResourceNotFoundException e) {
            w1953903logger.warn("Appointment not found: " + appointmentId);
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        } catch (Exception e) {
            w1953903logger.error("Failed to retrieve appointment: " + e.getMessage(), e);
            throw new ResourceNotFoundException("Failed to retrieve appointment: " + e.getMessage());
        }
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAppointment(Appointment appointment) {
        try {
            appointmentDAO.addAppointments(appointment);
            w1953903logger.info("New appointment added..");
            return Response.status(Response.Status.CREATED).entity(appointment).build();
        } catch (Exception e) {
            w1953903logger.error("Failed to add appointment: " + e.getMessage(), e);
            throw new ResourceNotFoundException("Failed to add appointment: " + e.getMessage());
        }
    }

    @PUT
    @Path("/{appointmentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAppointment(@PathParam("appointmentId") int appointmentId, Appointment updatedAppointment) {
        try {
            Appointment existingAppointment = appointmentDAO.getAppointmentById(appointmentId);
            if (existingAppointment != null) {
                updatedAppointment.setAppointmentid(appointmentId);
                appointmentDAO.updateAppointment(updatedAppointment);
                w1953903logger.info("Appointment updated: " + appointmentId);
                return Response.noContent().entity("{\"message\": \"Appointment updated successfully.\"}").build();
            } else {
                w1953903logger.warn("Appointment not found for updating: " + appointmentId);
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\": \"Appointment not found.\"}")
                        .build();
            }
        } catch (Exception e) {
            w1953903logger.error("Failed to update appointment: " + e.getMessage(), e);
            throw new ResourceNotFoundException("Failed to update appointment: " + e.getMessage());
        }
    }

    @DELETE
    @Path("/{appointmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAppointment(@PathParam("appointmentId") int appointmentId) {
        try {
            boolean deleted = appointmentDAO.deleteAppointment(appointmentId);
            if (deleted) {
                w1953903logger.info("Appointment deleted: " + appointmentId);
                // Return a message indicating successful deletion
                return Response.ok("{\"message\": \"Appointment with ID " + appointmentId + " deleted successfully.\"}").build();
            } else {
                w1953903logger.warn("Appointment not found for deletion: " + appointmentId);
                throw new ResourceNotFoundException("Appointment with ID " + appointmentId + " not found");
            }
        } catch (Exception e) {
            w1953903logger.error("Failed to delete appointment: " + e.getMessage(), e);
            throw new ResourceNotFoundException("Failed to delete appointment: " + e.getMessage());
        }
    }
}
