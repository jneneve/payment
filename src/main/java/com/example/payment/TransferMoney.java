package com.example.payment;

public class TransferMoney {

	private final AccountRepository accountRepository;

	public TransferMoney(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public void execute(TransferMoneyInputDTO input) {
		Account sourceAccount = this.accountRepository.getAccountById(input.sourceAccountId());
		Account targetAccount = this.accountRepository.getAccountById(input.targetAccountId());
		sourceAccount.withdraw(input.balance());
		targetAccount.addBalance(input.balance());
		this.accountRepository.updateAccount(sourceAccount);
		this.accountRepository.updateAccount(targetAccount);
	}
}
