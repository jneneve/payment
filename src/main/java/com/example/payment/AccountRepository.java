package com.example.payment;

public interface AccountRepository {

    public void save(Account account);

    public Account getAccountByEmail(String email);

    public Account getAccountByIdentificationNumber(String identificationNumber);

    public Account getAccountById(String accountId);

    public void updateAccount(Account account);
}
