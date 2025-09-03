package com.example.priceservice;

public enum CurrencyCode {
    USD("$", Placement.RIGHT),
    ILS("₪", Placement.LEFT);

    public enum Placement { LEFT, RIGHT }
    public final String symbol;
    public final Placement placement;

    CurrencyCode(String symbol, Placement placement) {
        this.symbol = symbol;
        this.placement = placement;
    }
}
