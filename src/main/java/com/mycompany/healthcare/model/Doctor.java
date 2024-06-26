/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.healthcare.model;



/**
 *
 * @author Dell
 */
public class Doctor extends Person{
    private String healthCareProffesional;
    private String specialization;
    private String contactDetails;
    private int doctorid;
    private Appointment appointment;
    private Patient patient;

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
   }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    public int getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(int doctorid) {
        this.doctorid = doctorid;
    }
    public Doctor(){}
    public Doctor(String healthCareProffesional, String specialization, String contactDetails, int doctorid,String name, int contact, String address, int personid,String date, String time,Patient patient) {
        super(name, contact, address, personid, date, time);
        this.healthCareProffesional = healthCareProffesional;
        this.specialization = specialization;
        this.contactDetails = contactDetails;
        this.doctorid=doctorid;
        
        this.patient=patient;
    }
    

    public String getHealthCareProffesional() {
        return healthCareProffesional;
    }

    public void setHealthCareProffesional(String healthCareProffesional) {
        this.healthCareProffesional = healthCareProffesional;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }
    
}
