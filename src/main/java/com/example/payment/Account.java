package com.example.payment;

import java.util.UUID;

import lombok.Getter;

@Getter
public class Account {

    private final UUID accountId;

    private final String name;

    private final String identificationNumber;

    private final String email;

    private final Integer password;

    private Long baselineBalance;

    private Account(UUID accountId, String name, String identificationNumber, String email, Integer password,
                    Long baselineBalance) {
        this.accountId = accountId;
        this.name = name;
        this.identificationNumber = identificationNumber;
        this.email = email;
        this.password = password;
        this.baselineBalance = baselineBalance;
    }

    public static Account create(String name, String identificationNumber, String email, Integer password) {
        UUID accountId = UUID.randomUUID();
        Long baselineBalance = 0L;
        return new Account(accountId, name, identificationNumber, email, password, baselineBalance);
    }

    public static Account restore(UUID accountId, String name, String identificationNumber, String email,
                                  Integer password, Long baselineBalance) {
        return new Account(accountId, name, identificationNumber, email, password, baselineBalance);
    }

    public void deposit(Long balance) {
        this.baselineBalance = balance;
    }

    public void withdraw(Long balance) {
        if (this.baselineBalance <= 0) {
            throw new IllegalStateException("The account does not have enough balance to make a withdrawal");
        }
        this.baselineBalance -= balance;
    }
}
