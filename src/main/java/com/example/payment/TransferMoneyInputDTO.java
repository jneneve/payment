package com.example.payment;

public record TransferMoneyInputDTO(String sourceAccountId, String targetAccountId, Long balance) {
}
