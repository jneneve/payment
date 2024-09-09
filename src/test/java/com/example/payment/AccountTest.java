package com.example.payment;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AccountTest {

    @Test
    @Description("Deve subtrair o valor de saldo com base no valor do saque")
    public void reducedBasedOnTheWithdrawAmount() {
        Account account = Account.create("John Doe", "87748248800", "john.doe@gmail.com", 123456);
        account.deposit(100L);
        account.withdraw(50L);
        assertThat(account.getBaselineBalance()).isEqualTo(50L);
    }

    @Test
    @Description("Não deve ser realizado um saque se não houver saldo na conta")
    public void throwAnExceptionIfThereIsNoBalanceInTheAccount() {
        Account account = Account.create("John Doe", "87748248800", "john.doe@gmail.com", 123456);
        assertThatThrownBy(() -> account.withdraw(50L)).isInstanceOf(IllegalStateException.class)
                .hasMessage("The account does not have enough balance to make a withdrawal");
    }
}
