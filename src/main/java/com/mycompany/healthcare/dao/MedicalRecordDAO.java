/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.healthcare.dao;

import com.mycompany.healthcare.model.MedicalRecord;
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
public class MedicalRecordDAO {
    private static final Logger w1953903logger = LoggerFactory.getLogger(MedicalRecordDAO.class);
    private static List<MedicalRecord> medicalRecords = new ArrayList<>();

    public List<MedicalRecord> getAllMedicalRecords() {
        w1953903logger.info("Fetching all medical records...");
        return medicalRecords;
    }
    static {
        // Adding some static data for testing - MedicalRecord 1
        Patient patientDetails1 = new Patient(/* Initialize patient details */);
        String diagnoses1 = "Some diagnosis 1";
        String treatments1 = "Some treatment 1";
        int medicalRecordid1 = 1;

        MedicalRecord medicalRecord1 = new MedicalRecord(patientDetails1, diagnoses1, treatments1, medicalRecordid1);
        medicalRecords.add(medicalRecord1);

        // Adding some static data for testing - MedicalRecord 2
        Patient patientDetails2 = new Patient(/* Initialize patient details */);
        String diagnoses2 = "Some diagnosis 2";
        String treatments2 = "Some treatment 2";
        int medicalRecordid2 = 2;

        MedicalRecord medicalRecord2 = new MedicalRecord(patientDetails2, diagnoses2, treatments2, medicalRecordid2);
        medicalRecords.add(medicalRecord2);
    }

    public MedicalRecord getMediaRecordById(int id) {
        w1953903logger.info("Fetching medical record with ID: " + id);
        for (MedicalRecord medicalRecord : medicalRecords) {
            if (medicalRecord.getMedicalRecordid() == id) {
                w1953903logger.info("Medical record found with ID: " + id);
                return medicalRecord;
            }
        }
        w1953903logger.warn("Medical record not found with ID: " + id);
        throw new ResourceNotFoundException("Medical Record with ID " + id + " not found");
    }

    public void addMedicalRecord(MedicalRecord medicalRecord) {
        int newMedicalRecordId = getNextMedicalRecordId();
        medicalRecord.setMedicalRecordid(newMedicalRecordId);
        medicalRecords.add(medicalRecord);
        w1953903logger.info("New medical record added with ID: " + newMedicalRecordId);
    }

    public void updateMedicalRecord(MedicalRecord updatedMedicalRecord) {
        for (int i = 0; i < medicalRecords.size(); i++) {
            MedicalRecord medicalRecord = medicalRecords.get(i);
            if (medicalRecord.getMedicalRecordid() == updatedMedicalRecord.getMedicalRecordid()) {
                medicalRecords.set(i, updatedMedicalRecord);
                w1953903logger.info("Medical record updated with ID: " + updatedMedicalRecord.getMedicalRecordid());
                return;
            }
        }
        w1953903logger.warn("Medical record not found for updating with ID: " + updatedMedicalRecord.getMedicalRecordid());
        throw new ResourceNotFoundException("Medical Record with ID " + updatedMedicalRecord.getMedicalRecordid() + " not found");
    }

    public boolean deleteMedicalRecord(int id) {
        boolean removed = medicalRecords.removeIf(medicalRecord -> medicalRecord.getMedicalRecordid() == id);
        if (removed) {
            w1953903logger.info("Medical record deleted with ID: " + id);
            return true;
        } else {
            w1953903logger.warn("Medical record not found for deletion with ID: " + id);
            throw new ResourceNotFoundException("Medical Record with ID " + id + " not found");
        }
    }

    public int getNextMedicalRecordId() {
        int maxMedicalRecordId = Integer.MIN_VALUE;
        for (MedicalRecord medicalRecord : medicalRecords) {
            int medicalRecordId = medicalRecord.getMedicalRecordid();
            if (medicalRecordId > maxMedicalRecordId) {
                maxMedicalRecordId = medicalRecordId;
            }
        }
        return maxMedicalRecordId + 1;
    }
}