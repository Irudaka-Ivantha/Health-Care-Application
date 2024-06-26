/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.healthcare.dao;

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
public class PatientDAO {
    private static final Logger w1953903logger = LoggerFactory.getLogger(PatientDAO.class);
    private static List<Patient> patients = new ArrayList<>();
    
    public List<Patient> getAllPatients() {
        w1953903logger.info("Fetching all patients...");
        return patients;
    }
    static {
        // Adding some static data for testing - Patient 1
        String medicalHistory1 = "Good";
        String currentHealth1 = "Average";
        int patientid1 = 1;
        String name1 = "John Doe";
        int contact1 = 123456890;
        String address1 = "123 Main St";
        int personid1 = 1;
        String date1 = "2024-05-06";
        String time1 = "09:00:00";

        Patient patient1 = new Patient(medicalHistory1, currentHealth1, patientid1, name1, contact1, address1, personid1, date1, time1);
        patients.add(patient1);

        // Adding some static data for testing - Patient 2
        String medicalHistory2 = "Bad";
        String currentHealth2 = "Goods";
        int patientid2 = 2;
        String name2 = "Jane Smith";
        int contact2 = 987654320;
        String address2 = "456 Oak St";
        int personid2 = 2;
        String date2 = "2024-05-07";
        String time2 = "10:30:00";

        Patient patient2 = new Patient(medicalHistory2, currentHealth2, patientid2, name2, contact2, address2, personid2, date2, time2);
        patients.add(patient2);
    }

    public Patient getPatientById(int id) {
        for (Patient patient : patients) {
            if (patient.getPatientid() == id) {
                w1953903logger.info("Patient found with ID: " + id);
                return patient;
            }
        }
        w1953903logger.warn("Patient not found with ID: " + id);
        throw new ResourceNotFoundException("Patient with ID " + id + " not found");
    }

    public void addPatient(Patient patient) {
        int newPatientId = getNextPatientId();
        patient.setPatientid(newPatientId);
        patients.add(patient);
        w1953903logger.info("New patient added with ID: " + newPatientId);
    }

    public void updatePatient(Patient updatedPatient) {
        boolean found = false;
        for (int i = 0; i < patients.size(); i++) {
            Patient patient = patients.get(i);
            if (patient.getPatientid() == updatedPatient.getPatientid()) {
                patients.set(i, updatedPatient);
                found = true;
                w1953903logger.info("Patient updated: " + updatedPatient);
                break;
            }
        }
        if (!found) {
            w1953903logger.warn("Patient not found for updating with ID: " + updatedPatient.getPatientid());
            throw new ResourceNotFoundException("Patient with ID " + updatedPatient.getPatientid() + " not found");
        }
    }

    public boolean deletePatient(int id) {
        boolean removed = patients.removeIf(patient -> patient.getPatientid() == id);
        if (removed) {
            w1953903logger.info("Patient deleted with ID: " + id);
            return true;
        } else {
            w1953903logger.warn("Patient not found for deletion with ID: " + id);
            throw new ResourceNotFoundException("Patient with ID " + id + " not found");
        }
    }

    public int getNextPatientId() {
        int maxPatientId = Integer.MIN_VALUE;
        for (Patient patient : patients) {
            int patientId = patient.getPatientid();
            if (patientId > maxPatientId) {
                maxPatientId = patientId;
            }
        }
        return maxPatientId + 1;
    }
}