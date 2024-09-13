package com.example.payment;

import lombok.Getter;

import java.util.UUID;

@Getter
public abstract class Account {

    private final UUID accountId;

    private final String name;

    private final String identificationNumber;

    private final String email;

    private final Integer password;

    private Long baselineBalance;

    private final String type;

    public Account(UUID accountId, String name, String identificationNumber, String email, Integer password,
                   Long baselineBalance, String type) {
        this.accountId = accountId;
        this.name = name;
        this.identificationNumber = identificationNumber;
        this.email = email;
        this.password = password;
        this.baselineBalance = baselineBalance;
        this.type = type;
    }

    protected void updateBaselineBalance(Long amount) {
        this.baselineBalance = amount;
    }
}
