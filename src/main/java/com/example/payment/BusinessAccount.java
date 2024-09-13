package com.example.payment;

import java.util.UUID;

public class BusinessAccount extends Account {

    public BusinessAccount(UUID accountId, String name, String identificationNumber, String email, Integer password,
                           Long baselineBalance, String type) {
        super(accountId, name, identificationNumber, email, password, baselineBalance, type);
    }

    public static BusinessAccount create(String name, String identificationNumber, String email, Integer password) {
        UUID accountId = UUID.randomUUID();
        Long baselineBalance = 0L;
        return new BusinessAccount(accountId, name, identificationNumber, email, password,
                baselineBalance, "business");
    }

    public void deposit(Long amount) {
        this.updateBaselineBalance(this.getBaselineBalance() + amount);
    }
}
