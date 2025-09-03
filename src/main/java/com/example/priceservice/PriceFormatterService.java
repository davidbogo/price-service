package com.example.priceservice;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

@Service
public class PriceFormatterService {
    private static final BigDecimal VAT_RATE = new BigDecimal("0.19");

    public PriceResponse format(String cents, CurrencyCode code) {
        BigDecimal gross = new BigDecimal(cents).movePointLeft(2); // cents -> units
        BigDecimal net   = gross.divide(BigDecimal.ONE.add(VAT_RATE), 2, RoundingMode.HALF_UP);
        BigDecimal vat   = gross.subtract(net);

        String plain = formatNumber(gross);
        String withCurrency = (code.placement == CurrencyCode.Placement.LEFT)
                ? code.symbol + plain
                : plain + code.symbol;

        PriceResponse r = new PriceResponse();
        r.setValue(gross.stripTrailingZeros());
        r.setFormattedValue(plain);
        r.setFormattedWithCurrency(withCurrency);
        r.setNetValue(net.stripTrailingZeros());
        r.setVatAmount(vat.stripTrailingZeros());
        return r;
    }

    private String formatNumber(BigDecimal n) {
        DecimalFormatSymbols sym = DecimalFormatSymbols.getInstance(Locale.US);
        DecimalFormat df = new DecimalFormat("#,##0.##", sym); // commas, dot, no trailing .00
        return df.format(n);
    }
}
