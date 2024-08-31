package com.example.payment;

import java.util.UUID;

import lombok.Getter;

public class Account {

	@Getter
	private UUID accountId;

	@Getter
	private String name;

	@Getter
	private String identificationNumber;

	@Getter
	private String email;

	@Getter
	private Integer password;

	@Getter
	private Long baselineBalance;

	private Account(UUID accountId, String name, String identificationNumber, String email, Integer password,
			Long baselineBalance) {
		super();
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
	
	public void addBalance(Long balance) {
		this.baselineBalance = balance;
	}
}
