package com.example.payment;

public class SignUp {

	private final AccountRepository accountRepository;

	public SignUp(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public String execute(SignUpInputDTO input) {
		Account existingEmailAccount = accountRepository.getAccountByEmail(input.email());
		if (existingEmailAccount != null)
			throw new IllegalStateException("This email has already been used!");
		Account existingInformationNumberAccount = accountRepository
				.getAccountByIdentificationNumber(input.identificationNumber());
		if (existingInformationNumberAccount != null)
			throw new IllegalStateException("This identification number has already been used!");
		Account account = Account.create(input.name(), input.identificationNumber(), input.email(), input.password());
		accountRepository.save(account);
		return account.getAccountId().toString();
	}
}
