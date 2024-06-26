/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.healthcare.model;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Dell
 */
public class Appointment {
    private Date date;
    private Time time;
    private Patient patient;
    private Doctor doctor;
    private int numOfParticipants;
    private int appointmentid;

    public int getAppointmentid() {
        return appointmentid;
    }

    public void setAppointmentid(int appointmentid) {
        this.appointmentid = appointmentid;
    }
    
    public Appointment(){}
    public Appointment(Date date, Time time, Patient patient, Doctor doctor, int numOfParticipants,int appointmentid) {
        this.date = date;
        this.time = time;
        this.patient = patient;
        this.doctor = doctor;
        this.numOfParticipants = numOfParticipants;
        this.appointmentid=appointmentid;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public int getNumOfParticipants() {
        return numOfParticipants;
    }

    public void setNumOfParticipants(int numOfParticipants) {
        this.numOfParticipants = numOfParticipants;
    }
    
    
}
