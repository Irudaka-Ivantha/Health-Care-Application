/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.healthcare.model;

import java.time.LocalDateTime;

/**
 *
 * @author Dell
 */
public class Patient extends Person{
    private String medicalHistory;
    private String currentHealth;
    private int patientid;
    
    public int getPatientid() {
        return patientid;
    }

    public void setPatientid(int patientid) {
        this.patientid = patientid;
    }
    
    public Patient(){}
    public Patient(String medicalHistory, String currentHealth,int patientid, String name, int contact, String address, int personid,String date, String time) {
        super(name, contact, address, personid,date, time);
        this.medicalHistory = medicalHistory;
        this.currentHealth = currentHealth;
        this.patientid=patientid;
    }
    
    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(String currentHealth) {
        this.currentHealth = currentHealth;
    }
    
}
