package com.example.payment;

import java.util.UUID;

public class PersonalAccount extends Account {

    public PersonalAccount(UUID accountId, String name, String identificationNumber, String email, Integer password,
                           Long baselineBalance, String type) {
        super(accountId, name, identificationNumber, email, password, baselineBalance, type);
    }

    public static PersonalAccount create(String name, String identificationNumber, String email, Integer password) {
        UUID accountId = UUID.randomUUID();
        Long baselineBalance = 0L;
        return new PersonalAccount(accountId, name, identificationNumber, email, password,
                baselineBalance, "personal");
    }

    public void deposit(Long amount) {
        this.updateBaselineBalance(this.getBaselineBalance() + amount);
    }

    public void withdraw(Long amount) {
        if (this.getBaselineBalance() <= 0) {
            throw new IllegalStateException("The account does not have enough balance to make a withdrawal");
        }
        this.updateBaselineBalance(this.getBaselineBalance() - amount);
    }
}
