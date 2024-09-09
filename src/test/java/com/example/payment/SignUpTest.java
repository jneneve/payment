package com.example.payment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

public class SignUpTest {

    @Test
    @Description("Deve criar uma conta para o usuário")
    public void signUp() {
        String email = "john.doe" + Math.random() + "@gmail.com";
        SignUpInputDTO signUpInputDTO = new SignUpInputDTO("John Doe", "87748248800", email, 123456);
        AccountRepository accountRepository = new AccountRepositoryMemory();
        SignUp signUp = new SignUp(accountRepository);
        String accountId = signUp.execute(signUpInputDTO);
        assertThat(accountId).isNotNull();
    }

    @Test
    @Description("Não deve criar uma conta para um usuário utilizando um e-mail que já esteja cadastrado")
    public void throwAnExceptionIfTheEmailHasAlreadyBeenUsed() {
        String email = "john.doe" + Math.random() + "@gmail.com";
        SignUpInputDTO signUpInputDTO = new SignUpInputDTO("John Doe", "87748248800", email, 123456);
        AccountRepository accountRepository = new AccountRepositoryMemory();
        SignUp signUp = new SignUp(accountRepository);
        signUp.execute(signUpInputDTO);
        assertThatThrownBy(() -> signUp.execute(signUpInputDTO)).isInstanceOf(IllegalStateException.class)
                .hasMessage("This email has already been used!");
    }

    @Test
    @Description("Não deve criar uma conta para um usuário utilizando o mesmo CPF/CNPJ que já esteja cadastrado")
    public void throwAnExceptionIfTheIndentificationNumberHasAlreadyBeenUsed() {
        String email = "john.doe" + Math.random() + "@gmail.com";
        SignUpInputDTO signUpInputDTO = new SignUpInputDTO("John Doe", "87748248800", email, 123456);
        AccountRepository accountRepository = new AccountRepositoryMemory();
        SignUp signUp = new SignUp(accountRepository);
        signUp.execute(signUpInputDTO);
        String emailForNewAccount = "john.doe" + Math.random() + "@gmail.com";
        SignUpInputDTO signUpInputDTOForAnotherAccount = new SignUpInputDTO("John Doe", "87748248800",
                emailForNewAccount, 123456);
        assertThatThrownBy(() -> signUp.execute(signUpInputDTOForAnotherAccount))
                .isInstanceOf(IllegalStateException.class).hasMessage("This identification number has already been used!");
    }
}
