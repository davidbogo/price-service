package com.example.priceservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PriceServiceApplicationTests {

  @Autowired MockMvc mvc;

  // --- existing sanity tests ---

  @Test void currencies_ok() throws Exception {
    mvc.perform(get("/currencies"))
       .andExpect(status().isOk())
       .andExpect(content().json("[\"USD\",\"ILS\"]"));
  }

  @Test void format_price_json() throws Exception {
    mvc.perform(post("/format-price")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"amount\":\"100000\",\"currency\":\"ILS\"}"))
       .andExpect(status().isOk())
       .andExpect(jsonPath("$.value").value(1000))
       .andExpect(jsonPath("$.formattedWithCurrency").value("₪1,000"));
  }

  // --- new tests you asked for ---

  @Test void format_price_xml_usd() throws Exception {
    // Request body as JSON is fine; Accept XML drives the response format
    mvc.perform(post("/format-price")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_XML)
            .content("{\"amount\":\"100\",\"currency\":\"USD\"}"))
       .andExpect(status().isOk())
       // Basic XML assertions (string match is enough here)
       .andExpect(content().string(org.hamcrest.Matchers.containsString("<PriceResponse")))
       .andExpect(content().string(org.hamcrest.Matchers.containsString("<formattedWithCurrency>1$</formattedWithCurrency>")))
       .andExpect(content().string(org.hamcrest.Matchers.containsString("<value>1</value>")));
  }

  @Test void validation_rejects_nondigit_amount() throws Exception {
    mvc.perform(post("/format-price")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"amount\":\"12.3\",\"currency\":\"USD\"}"))
       .andExpect(status().isBadRequest());
  }

  @Test void unsupported_currency_returns_400() throws Exception {
    mvc.perform(post("/format-price")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"amount\":\"100\",\"currency\":\"EUR\"}"))
       .andExpect(status().isBadRequest());
  }
}
