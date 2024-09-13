package com.example.payment;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AccountTest {

    @Test
    @Description("Deve adicionar saldo à conta física")
    public void addBalanceToPersonalAccount() {
        PersonalAccount personalAccount = PersonalAccount.create("John Doe", "87748248800",
                "john.doe@gmail.com", 123456);
        personalAccount.deposit(50L);
        assertThat(personalAccount.getBaselineBalance()).isEqualTo(50L);
    }

    @Test
    @Description("Deve adicionar saldo à conta jurídica")
    public void addBalanceToBusinessAccount() {
        BusinessAccount businessAccount = BusinessAccount.create("John Doe", "47714316000109",
                "john.doe@gmail.com", 123456);
        businessAccount.deposit(50L);
        assertThat(businessAccount.getBaselineBalance()).isEqualTo(50L);
    }

    @Test
    @Description("Deve subtrair o saldo da conta física com base no valor do saque")
    public void reducedBasedOnTheWithdrawAmount() {
        PersonalAccount personalAccount = PersonalAccount.create("John Doe", "87748248800",
                "john.doe@gmail.com", 123456);
        personalAccount.deposit(100L);
        personalAccount.withdraw(50L);
        assertThat(personalAccount.getBaselineBalance()).isEqualTo(50L);
    }

    @Test
    @Description("Não deve ser realizado um saque se não houver saldo na conta")
    public void throwAnExceptionIfThereIsNoBalanceInTheAccount() {
        PersonalAccount personalAccount = PersonalAccount.create("John Doe", "87748248800",
                "john.doe@gmail.com", 123456);
        assertThatThrownBy(() -> personalAccount.withdraw(50L)).isInstanceOf(IllegalStateException.class)
                .hasMessage("The account does not have enough balance to make a withdrawal");
    }
}
