package com.mycompany.healthcare.resource;

import com.mycompany.healthcare.dao.AppointmentDAO;
import com.mycompany.healthcare.dao.DoctorDAO;
import com.mycompany.healthcare.dao.PatientDAO;
import com.mycompany.healthcare.model.Appointment;
import com.mycompany.healthcare.model.Doctor;
import com.mycompany.healthcare.model.Patient;
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

@Path("/doctor")
public class DoctorResource {
    private static final Logger w1953903logger = LoggerFactory.getLogger(DoctorResource.class);
    private DoctorDAO doctorDAO = new DoctorDAO();

    @GET
    @Path("/getall")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDoctors() {
        try {
            List<Doctor> doctors = doctorDAO.getAllDoctors();
            w1953903logger.info("All doctors fetched..");
            return Response.ok(doctors).build();
        } catch (Exception e) {
            w1953903logger.error("Failed to retrieve all doctors: " + e.getMessage(), e);
            throw new ResourceNotFoundException("Failed to retrieve all doctors: " + e.getMessage());
        }
    }

    @GET
    @Path("/{doctorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDoctorById(@PathParam("doctorId") int doctorId) {
        try {
            Doctor doctor = doctorDAO.getDoctorById(doctorId);
            if (doctor != null) {
                w1953903logger.info("Doctor fetched: " + doctorId);
                return Response.ok(doctor).build();
            } else {
                w1953903logger.warn("Doctor not found: " + doctorId);
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            w1953903logger.error("Failed to retrieve doctor: " + e.getMessage(), e);
            throw new ResourceNotFoundException("Failed to retrieve doctor: " + e.getMessage());
        }
    }

    @POST
    @Path("/addDoctor")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addDoctor(Doctor doctor) {
        try {
            doctorDAO.addDoctor(doctor);
            w1953903logger.info("New doctor added..");
            return Response.status(Response.Status.CREATED).entity(doctor).build();
        } catch (Exception e) {
            w1953903logger.error("Failed to add doctor: " + e.getMessage(), e);
            throw new ResourceNotFoundException("Failed to add doctor: " + e.getMessage());
        }
    }

    @PUT
@Path("/{doctorId}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response updateDoctor(@PathParam("doctorId") int doctorId, Doctor updatedDoctor) {
    try {
        Doctor existingDoctor = doctorDAO.getDoctorById(doctorId);
        if (existingDoctor != null) {
            updatedDoctor.setDoctorid(doctorId);
            doctorDAO.updateDoctor(updatedDoctor);
            w1953903logger.info("Doctor updated: " + doctorId);
            return Response.ok(updatedDoctor).build(); // Return the updated doctor in the response
        } else {
            w1953903logger.warn("Doctor not found for updating: " + doctorId);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    } catch (Exception e) {
        w1953903logger.error("Failed to update doctor: " + e.getMessage(), e);
        throw new ResourceNotFoundException("Failed to update doctor: " + e.getMessage());
    }
}

@DELETE
@Path("/{doctorId}")
@Produces(MediaType.APPLICATION_JSON)
public Response deleteDoctor(@PathParam("doctorId") int doctorId) {
    try {
        boolean deleted = doctorDAO.deleteDoctor(doctorId);
        if (deleted) {
            w1953903logger.info("Doctor deleted: " + doctorId);
            // Return a message indicating successful deletion
            return Response.ok("{\"message\": \"Doctor with ID " + doctorId + " deleted successfully.\"}").build();
        } else {
            w1953903logger.warn("Doctor not found for deletion: " + doctorId);
            throw new ResourceNotFoundException("Doctor with ID " + doctorId + " not found");
        }
    } catch (Exception e) {
        w1953903logger.error("Failed to delete doctor: " + e.getMessage(), e);
        throw new ResourceNotFoundException("Failed to delete doctor: " + e.getMessage());
    }
}

@GET
@Path("/{doctorId}/patient")
@Produces(MediaType.APPLICATION_JSON)
public Response getPatientForDoctor(@PathParam("doctorId") int doctorId) {
    w1953903logger.info("Fetching patient for doctor with id: {}", doctorId);
    Doctor doctor = doctorDAO.getDoctorById(doctorId);
    if (doctor != null) {
        Patient patient = doctor.getPatient();
        if (patient != null) {
            w1953903logger.info("Patient found for doctor with id: {}", doctorId);
            return Response.ok(patient).build(); // Return the patient data in the response
        } else {
            w1953903logger.error("Patient not found for the doctor with id: {}", doctorId);
            throw new ResourceNotFoundException("Patient not found for the doctor.");
        }
    } else {
        w1953903logger.error("Doctor not found with id: {}", doctorId);
        throw new ResourceNotFoundException("Doctor not found.");
    }
}
}
//    @GET
//    @Path("/{doctorId}/appointment")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getAppointmentsForDoctor(@PathParam("doctorId") int doctorId) {
//        w1953903logger.info("Fetching appointments for doctor with id: {}", doctorId);
//        Doctor doctor = doctorDAO.getDoctorById(doctorId);
//        if (doctor != null) {
//            Appointment appointment = doctor.getAppointment();
//            if (appointment != null) {
//                w1953903logger.info("Appointments found for doctor with id: {}", doctorId);
//                return Response.ok(appointment).build();
//            } else {
//                w1953903logger.error("Appointment not found for the doctor with id: {}", doctorId);
//                throw new ResourceNotFoundException("Appointment not found for the doctor.");
//            }
//        } else {
//            w1953903logger.error("Doctor not found with id: {}", doctorId);
//            throw new ResourceNotFoundException("Doctor not found.");
//        }
//    }

    


