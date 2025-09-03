package com.example.priceservice;

import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class PriceController {
    private final PriceFormatterService svc;
    public PriceController(PriceFormatterService svc) { this.svc = svc; }

    @GetMapping("/currencies")
    public List<String> currencies() {
        return Arrays.stream(CurrencyCode.values()).map(Enum::name).toList();
    }

    @PostMapping(path = "/format-price", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public PriceResponse format(@Valid @RequestBody PriceRequest req) {
        CurrencyCode code = CurrencyCode.valueOf(req.getCurrency().toUpperCase());
        return svc.format(req.getAmount(), code);
    }
}
