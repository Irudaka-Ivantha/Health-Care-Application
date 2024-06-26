/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.healthcare.model;

/**
 *
 * @author Dell
 */
public class MedicalRecord {
    private Patient patientDetails;
    private String diagnoses;
    private String treatments;
    private int medicalRecordid;
    public int getMedicalRecordid() {
        return medicalRecordid;
    }

    public void setMedicalRecordid(int medicalRecordid) {
        this.medicalRecordid = medicalRecordid;
    }
    
    public MedicalRecord(){}
    public MedicalRecord(Patient patientDetails, String diagnoses, String treatments, int medicalRecordid) {
        this.patientDetails = patientDetails;
        this.diagnoses = diagnoses;
        this.treatments = treatments;
        this.medicalRecordid=medicalRecordid;
    }

    public Patient getPatientDetails() {
        return patientDetails;
    }

    public void setPatientDetails(Patient patientDetails) {
        this.patientDetails = patientDetails;
    }

    public String getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(String diagnoses) {
        this.diagnoses = diagnoses;
    }

    public String getTreatments() {
        return treatments;
    }

    public void setTreatments(String treatments) {
        this.treatments = treatments;
    } 
}
