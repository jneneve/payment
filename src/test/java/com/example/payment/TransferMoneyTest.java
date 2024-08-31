package com.example.payment;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

public class TransferMoneyTest {

	@Test
	@Description("Deve realizar uma transferência de saldo para outra conta")
	public void transferMoney() {
		AccountRepository accountRepository = new AccountRepositoryMemory();
		SignUp signUp = new SignUp(accountRepository);
		String sourceEmail = "john.doe" + Math.random() + "@gmail.com";
		SignUpInputDTO sourceSignUpInputDTO = new SignUpInputDTO("John Doe", "87748248800", sourceEmail, 123456);
		String sourceAccountId = signUp.execute(sourceSignUpInputDTO);
		String targetEmail = "jane.doe" + Math.random() + "@gmail.com";
		SignUpInputDTO targetSignUpInputDTO = new SignUpInputDTO("Jane Doe", "87748248801", targetEmail, 654321);
		String targetAccountId = signUp.execute(targetSignUpInputDTO);
		AddAccountBalance addAccountBalance = new AddAccountBalance(accountRepository);
		AddAccountBalanceInputDTO addAccountBalanceInputDTO = new AddAccountBalanceInputDTO(sourceAccountId, 100L);
		addAccountBalance.execute(addAccountBalanceInputDTO);
		TransferMoney transferMoney = new TransferMoney(accountRepository);
		TransferMoneyInputDTO transferMoneyInputDTO = new TransferMoneyInputDTO(sourceAccountId, targetAccountId, 50L);
		transferMoney.execute(transferMoneyInputDTO);
		GetAccountBalance getAccountBalance = new GetAccountBalance(accountRepository);
		Long balance = getAccountBalance.execute(targetAccountId);
		assertThat(balance).isEqualTo(50L);
	}
}
