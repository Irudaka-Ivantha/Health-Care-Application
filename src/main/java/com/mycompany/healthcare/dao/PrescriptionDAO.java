/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.healthcare.dao;

import com.mycompany.healthcare.model.Prescription;
import com.mycompany.healthcare.exception.ResourceNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Dell
 */
public class PrescriptionDAO {
    private static final Logger w1953903logger = LoggerFactory.getLogger(PrescriptionDAO.class);
    private static List<Prescription> prescriptions = new ArrayList<>();

    public List<Prescription> getAllPrescriptions() {
        return prescriptions;
    }
    static {
        // Adding some static data for testing - Prescription 1
        String medication1 = "Ativan";
        String dosage1 = "50mg";
        String instructions1 = "Once a day";
        int duration1 = 7;
        int prescriptionid1 = 1;

        Prescription prescription1 = new Prescription(medication1, dosage1, instructions1, duration1, prescriptionid1);
        prescriptions.add(prescription1);

        // Adding some static data for testing - Prescription 2
        String medication2 = "Amoxicillin";
        String dosage2 = "10mg";
        String instructions2 = "twice a day";
        int duration2 = 10;
        int prescriptionid2 = 2;

        Prescription prescription2 = new Prescription(medication2, dosage2, instructions2, duration2, prescriptionid2);
        prescriptions.add(prescription2);
    }

    public Prescription getPrescriptionById(int id) {
        for (Prescription prescription : prescriptions) {
            if (prescription.getPrescriptionid() == id) {
                return prescription;
            }
        }
        w1953903logger.warn("Prescription not found with ID: {}", id);
        throw new ResourceNotFoundException("Prescription not found with ID: " + id);
    }

    public void addPrescription(Prescription prescription) {
        int newPrescriptionId = getNextPrescriptionId();
        prescription.setPrescriptionid(newPrescriptionId);
        prescriptions.add(prescription);
        w1953903logger.info("Prescription added: {}", prescription);
    }

    public void updatePrescription(Prescription updatedPrescription) {
        for (int i = 0; i < prescriptions.size(); i++) {
            Prescription billing = prescriptions.get(i);
            if (billing.getPrescriptionid() == updatedPrescription.getPrescriptionid()) {
                prescriptions.set(i, updatedPrescription);
                w1953903logger.info("Prescription updated: {}", updatedPrescription);
                return;
            }
        }
        w1953903logger.warn("Prescription not found for updating with ID: {}", updatedPrescription.getPrescriptionid());
        throw new ResourceNotFoundException("Prescription not found with ID: " + updatedPrescription.getPrescriptionid());
    }

    public boolean deletePrescription(int id) {
        boolean removed = prescriptions.removeIf(prescription -> prescription.getPrescriptionid() == id);
        if (!removed) {
            w1953903logger.warn("Prescription not found for deletion with ID: {}", id);
            throw new ResourceNotFoundException("Prescription not found with ID: " + id);
        }
        w1953903logger.info("Prescription deleted with ID: {}", id);
        return true;
    }

    public int getNextPrescriptionId() {
        int maxPrescriptionId = Integer.MIN_VALUE;
        for (Prescription prescription : prescriptions) {
            int prescriptionId = prescription.getPrescriptionid();
            if (prescriptionId > maxPrescriptionId) {
                maxPrescriptionId = prescriptionId;
            }
        }
        return maxPrescriptionId + 1;
    }
}