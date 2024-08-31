package com.example.payment;

import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryMemory implements AccountRepository {

	List<Account> accounts = new ArrayList<Account>();

	@Override
	public void save(Account account) {
		accounts.add(account);
	}

	@Override
	public Account getAccountByEmail(String email) {
		for (Account account : accounts) {
			if (account.getEmail().equals(email)) {
				return Account.restore(account.getAccountId(), account.getName(), account.getIdentificationNumber(),
						account.getEmail(), account.getPassword(), account.getBaselineBalance());
			}
		}
		return null;
	}

	@Override
	public Account getAccountByIdentificationNumber(String identificationNumber) {
		for (Account account : accounts) {
			if (account.getIdentificationNumber().equals(identificationNumber)) {
				return Account.restore(account.getAccountId(), account.getName(), account.getIdentificationNumber(),
						account.getEmail(), account.getPassword(), account.getBaselineBalance());
			}
		}
		return null;
	}

	@Override
	public Account getAccountById(String accountId) {
		for (Account account : accounts) {
			String formattedAccountId = account.getAccountId().toString();
			if (formattedAccountId.equals(accountId)) {
				return Account.restore(account.getAccountId(), account.getName(), account.getIdentificationNumber(),
						account.getEmail(), account.getPassword(), account.getBaselineBalance());
			}
		}
		return null;
	}

	@Override
	public void updateAccount(Account account) {
		accounts.removeIf(existingAccount -> existingAccount.getAccountId().equals(account.getAccountId()));
		accounts.add(account);
	}
}
