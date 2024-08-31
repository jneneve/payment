package com.example.payment;

public class AddAccountBalance {

	private final AccountRepository accountRepository;

	public AddAccountBalance(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	public void execute(AddAccountBalanceInputDTO input) {
		Account existingAccount = this.accountRepository.getAccountById(input.accountId());
		existingAccount.addBalance(input.balance());
		this.accountRepository.updateAccount(existingAccount);
	}
}
