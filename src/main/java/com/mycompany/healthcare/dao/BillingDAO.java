/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.healthcare.dao;

import com.mycompany.healthcare.model.Billing;
import com.mycompany.healthcare.exception.ResourceNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Dell
 */
public class BillingDAO {
    private static final Logger w1953903logger = LoggerFactory.getLogger(BillingDAO.class);
    private static List<Billing> billings = new ArrayList<>();
    static {
        // Adding some static data for testing - Billing 1
        double invoiceAmount1 = 100.0;
        double payment1 = 50.0;
        double balance1 = 50.0;
        int billingid1 = 1;

        Billing billing1 = new Billing(invoiceAmount1, payment1, balance1, billingid1);
        billings.add(billing1);

        // Adding some static data for testing - Billing 2
        double invoiceAmount2 = 200.0;
        double payment2 = 150.0;
        double balance2 = 50.0;
        int billingid2 = 2;

        Billing billing2 = new Billing(invoiceAmount2, payment2, balance2, billingid2);
        billings.add(billing2);
    }
    public List<Billing> getAllBillings() {
        w1953903logger.info("Fetching all billings...");
        return billings;
    }

    public Billing getBillingById(int id) {
        for (Billing billing : billings) {
            if (billing.getBillingid() == id) {
                w1953903logger.info("Billing found with ID: " + id);
                return billing;
            }
        }
        w1953903logger.error("Billing with ID " + id + " not found");
        throw new ResourceNotFoundException("Billing with ID " + id + " not found");
    }

    public void addBilling(Billing billing) {
        int newBillingId = getNextBillingId();
        billing.setBillingid(newBillingId);
        billings.add(billing);
        w1953903logger.info("New billing added: " + billing);
    }

    public void updateBilling(Billing updatedBilling) {
        for (int i = 0; i < billings.size(); i++) {
            Billing billing = billings.get(i);
            if (billing.getBillingid() == updatedBilling.getBillingid()) {
                billings.set(i, updatedBilling);
                w1953903logger.info("Billing updated: " + updatedBilling);
                return;
            }
        }
        w1953903logger.error("Billing with ID " + updatedBilling.getBillingid() + " not found");
        throw new ResourceNotFoundException("Billing with ID " + updatedBilling.getBillingid() + " not found");
    }

    public boolean deleteBilling(int id) {
        if (!billings.removeIf(billing -> billing.getBillingid() == id)) {
            w1953903logger.error("Billing with ID " + id + " not found");
            throw new ResourceNotFoundException("Billing with ID " + id + " not found");
        }
        w1953903logger.info("Billing deleted with ID: " + id);
        return true;
    }

    public int getNextBillingId() {
        // Initialize maxBillingId with a value lower than any possible billingId
        int maxBillingId = Integer.MIN_VALUE;

        // Iterate through the list to find the maximum billingId
        for (Billing billing : billings) {
            int billingId = billing.getBillingid();
            if (billingId > maxBillingId) {
                maxBillingId = billingId;
            }
        }

        // Increment the maximum billingId to get the next available billingId
        return maxBillingId + 1;
    }
}