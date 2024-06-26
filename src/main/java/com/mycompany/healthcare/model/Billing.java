/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.healthcare.model;

/**
 *
 * @author Dell
 */
public class Billing {
    private double invoiceAmount;
    private double payment;
    private double balance;
    private int billingid;
    public int getBillingid() {
        return billingid;
    }

    public void setBillingid(int billingid) {
        this.billingid = billingid;
    }
    public Billing(){}
    public Billing(double invoiceAmount, double payment, double balance,int billingid) {
        this.invoiceAmount = invoiceAmount;
        this.payment = payment;
        this.balance = balance;
        this.billingid=billingid;
    }

    public double getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


}
