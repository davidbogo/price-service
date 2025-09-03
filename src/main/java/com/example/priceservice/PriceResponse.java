package com.example.priceservice;

import java.math.BigDecimal;

public class PriceResponse {
    private BigDecimal value;
    private String formattedWithCurrency;
    private String formattedValue;
    private BigDecimal netValue;
    private BigDecimal vatAmount;

    public BigDecimal getValue() { return value; }
    public void setValue(BigDecimal value) { this.value = value; }
    public String getFormattedWithCurrency() { return formattedWithCurrency; }
    public void setFormattedWithCurrency(String v) { this.formattedWithCurrency = v; }
    public String getFormattedValue() { return formattedValue; }
    public void setFormattedValue(String v) { this.formattedValue = v; }
    public BigDecimal getNetValue() { return netValue; }
    public void setNetValue(BigDecimal netValue) { this.netValue = netValue; }
    public BigDecimal getVatAmount() { return vatAmount; }
    public void setVatAmount(BigDecimal vatAmount) { this.vatAmount = vatAmount; }
}
