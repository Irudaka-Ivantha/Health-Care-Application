/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.healthcare.dao;

import com.mycompany.healthcare.model.Appointment;
import com.mycompany.healthcare.model.Doctor;
import com.mycompany.healthcare.model.Patient;
import com.mycompany.healthcare.exception.ResourceNotFoundException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Dell
 */
public class AppointmentDAO {
    private static final Logger w1953903logger = LoggerFactory.getLogger(AppointmentDAO.class);
    private static List<Appointment> appointments = new ArrayList<>();

    public List<Appointment> getAllAppointments() {
        w1953903logger.info("Fetching all appointments...");
        return appointments;
    }
    static {
        // Adding some static data for testing - Appointment 1
        Date date1 = new Date(); // Replace with the actual date
        Time time1 = Time.valueOf("11:00:00"); // Replace with the actual time
        Patient patient1 = new Patient("Heart problem", "Stable", 1, "John Doe", 123456789, "123 Main St", 1, "2024-03-12", "11:00");
        Doctor doctor1 = new Doctor("Cardiologist", "Cardiology", "brown@gmail.com", 1, "Dr. Michael Brown", 741298656, "789 Maple Avenue", 1, "2024-03-12", "11:00",patient1);
        int numOfParticipants1 = 1; // Static value for number of participants
        int appointmentid1 = 1; // Static value for appointment ID

        Appointment appointment1 = new Appointment(date1, time1, patient1, doctor1, numOfParticipants1, appointmentid1);
        appointments.add(appointment1);

        // Adding some static data for testing - Appointment 2
        Date date2 = new Date(); // Replace with the actual date
        Time time2 = Time.valueOf("14:30:00"); // Replace with the actual time
        Patient patient2 = new Patient("Brain tumor", "Stable", 2, "Jane Smith", 987654321, "456 Oak St", 2, "2024-03-15", "14:30");
        Doctor doctor2 = new Doctor("Neurologist", "Neurology", "white@gmail.com", 2, "Dr. Emily White", 852147963, "456 Oak Street", 2, "2024-03-15", "14:30",patient2);
        int numOfParticipants2 = 1; // Static value for number of participants
        int appointmentid2 = 2; // Static value for appointment ID

        Appointment appointment2 = new Appointment(date2, time2, patient2, doctor2, numOfParticipants2, appointmentid2);
        appointments.add(appointment2);

        // Adding some static data for testing - Appointment 3
        Date date3 = new Date(); // Replace with the actual date
        Time time3 = Time.valueOf("09:30:00"); // Replace with the actual time
        Patient patient3 = new Patient("Skin allergy", "Stable", 3, "David Johnson", 123123123, "123 Pine St", 3, "2024-03-18", "09:30");
        Doctor doctor3 = new Doctor("Dermatologist", "Dermatology", "black@gmail.com", 3, "Dr. Sarah Black", 369852147, "123 Pine Street", 3, "2024-03-18", "09:30",patient3);
        int numOfParticipants3 = 1; // Static value for number of participants
        int appointmentid3 = 3; // Static value for appointment ID

        Appointment appointment3 = new Appointment(date3, time3, patient3, doctor3, numOfParticipants3, appointmentid3);
        appointments.add(appointment3);
    }

    public Appointment getAppointmentById(int id) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentid() == id) {
                w1953903logger.info("Appointment found with ID: " + id);
                return appointment;
            }
        }
        w1953903logger.error("Appointment with ID " + id + " not found");
        throw new ResourceNotFoundException("Appointment with ID " + id + " not found");
    }

    public void addAppointments(Appointment appointment) {
        int newAppointmentId = getNextAppointmentId();
        appointment.setAppointmentid(newAppointmentId);
        appointments.add(appointment);
        w1953903logger.info("New appointment added: " + appointment);
    }

    public void updateAppointment(Appointment updatedAppointment) {
        for (int i = 0; i < appointments.size(); i++) {
            Appointment appointment = appointments.get(i);
            if (appointment.getAppointmentid() == updatedAppointment.getAppointmentid()) {
                appointments.set(i, updatedAppointment);
                w1953903logger.info("Appointment updated: " + updatedAppointment);
                return;
            }
        }
        w1953903logger.error("Appointment with ID " + updatedAppointment.getAppointmentid() + " not found");
        throw new ResourceNotFoundException("Appointment with ID " + updatedAppointment.getAppointmentid() + " not found");
    }

    public boolean deleteAppointment(int id) {
        if (!appointments.removeIf(appointment -> appointment.getAppointmentid() == id)) {
            w1953903logger.error("Appointment with ID " + id + " not found");
            throw new ResourceNotFoundException("Appointment with ID " + id + " not found");
        }
        w1953903logger.info("Appointment deleted with ID: " + id);
        return true;
    }

    public int getNextAppointmentId() {
        // Initialize maxAppointmentId with a value lower than any possible appointmentId
        int maxAppointmentId = Integer.MIN_VALUE;

        // Iterate through the list to find the maximum appointmentId
        for (Appointment appointment : appointments) {
            int appointmentId = appointment.getAppointmentid();
            if (appointmentId > maxAppointmentId) {
                maxAppointmentId = appointmentId;
            }
        }

        // Increment the maximum appointmentId to get the next available appointmentId
        return maxAppointmentId + 1;
    }
}
