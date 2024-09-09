package com.example.payment;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

public class AddAccountBalanceTest {

    @Test
    @Description("Deve adicionar saldo a uma conta")
    public void addAccountBalance() {
        String email = "john.doe" + Math.random() + "@gmail.com";
        SignUpInputDTO signUpInputDTO = new SignUpInputDTO("John Doe", "87748248800", email, 123456);
        AccountRepository accountRepository = new AccountRepositoryMemory();
        SignUp signUp = new SignUp(accountRepository);
        String accountId = signUp.execute(signUpInputDTO);
        AddAccountBalanceInputDTO addAccountBalanceInputDTO = new AddAccountBalanceInputDTO(accountId, 100L);
        AddAccountBalance addAccountBalance = new AddAccountBalance(accountRepository);
        addAccountBalance.execute(addAccountBalanceInputDTO);
        GetAccountBalance getAccountBalance = new GetAccountBalance(accountRepository);
        Long balance = getAccountBalance.execute(accountId);
        assertThat(balance).isEqualTo(addAccountBalanceInputDTO.balance());
    }
}
