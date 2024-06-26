/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.healthcare.dao;

import com.mycompany.healthcare.model.Doctor;
import com.mycompany.healthcare.model.Patient;
import com.mycompany.healthcare.exception.ResourceNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Dell
 */
public class DoctorDAO {
    private static final Logger w1953903logger = LoggerFactory.getLogger(DoctorDAO.class);
    private static List<Doctor> doctors = new ArrayList<>();

    public List<Doctor> getAllDoctors() {
        w1953903logger.info("Fetching all doctors...");
        return doctors;
    }
    
    static {
        Patient patient1 = new Patient("Heart problem", "Stable", 1, "John Doe", 123456789, "123 Main St", 1, "2024-03-12", "11:00");
        // Adding some static data for testing
        doctors.add(new Doctor("Cardiologist", "Cardiology", "brown@gmail.com", 1, "Dr. Michael Brown", 741298656, "789 Maple Avenue", 1, "2024-03-12", "11:00",patient1));
    }

    public Doctor getDoctorById(int id) {
        for (Doctor doctor : doctors) {
            if (doctor.getDoctorid() == id) {
                w1953903logger.info("Doctor found with ID: " + id);
                return doctor;
            }
        }
        w1953903logger.error("Doctor with ID " + id + " not found");
        throw new ResourceNotFoundException("Doctor with ID " + id + " not found");
    }

    public void addDoctor(Doctor doctor) {
        int newDoctorId = getNextDoctorId();
        doctor.setDoctorid(newDoctorId);
        doctors.add(doctor);
        w1953903logger.info("New doctor added: " + doctor);
    }

    public void updateDoctor(Doctor updatedDoctor) {
        for (int i = 0; i < doctors.size(); i++) {
            Doctor doctor = doctors.get(i);
            if (doctor.getDoctorid() == updatedDoctor.getDoctorid()) {
                doctors.set(i, updatedDoctor);
                w1953903logger.info("Doctor updated: " + updatedDoctor);
                return;
            }
        }
        w1953903logger.error("Doctor with ID " + updatedDoctor.getDoctorid() + " not found");
        throw new ResourceNotFoundException("Doctor with ID " + updatedDoctor.getDoctorid() + " not found");
    }

    public boolean deleteDoctor(int id) {
        if (!doctors.removeIf(doctor -> doctor.getDoctorid() == id)) {
            w1953903logger.error("Doctor with ID " + id + " not found");
            throw new ResourceNotFoundException("Doctor with ID " + id + " not found");
        }
        w1953903logger.info("Doctor deleted with ID: " + id);
        return true;
    }

    public int getNextDoctorId() {
        // Initialize maxDoctorId with a value lower than any possible doctorId
        int maxDoctorId = Integer.MIN_VALUE;

        // Iterate through the list to find the maximum doctorId
        for (Doctor doctor : doctors) {
            int doctorId = doctor.getDoctorid();
            if (doctorId > maxDoctorId) {
                maxDoctorId = doctorId;
            }
        }

        // Increment the maximum doctorId to get the next available doctorId
        return maxDoctorId + 1;
    }
}
