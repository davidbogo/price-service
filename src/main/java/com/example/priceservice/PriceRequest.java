package com.example.priceservice;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class PriceRequest {
    @NotBlank
    @Pattern(regexp="\\d+", message="amount must be digits-only cents")
    private String amount;

    @NotBlank
    private String currency;

    public String getAmount() { return amount; }
    public void setAmount(String amount) { this.amount = amount; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
}
