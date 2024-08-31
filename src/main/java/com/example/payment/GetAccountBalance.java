package com.example.payment;

public class GetAccountBalance {

	private final AccountRepository accountRepository;

	public GetAccountBalance(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	public Long execute(String accountId) {
		Account existingAccount = this.accountRepository.getAccountById(accountId);
		return existingAccount.getBaselineBalance();
	}
}
