package com.example.priceservice;

import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {
  @Bean
  Jackson2ObjectMapperBuilder jacksonCustomizer() {
    return new Jackson2ObjectMapperBuilder()
        .featuresToEnable(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN);
  }
}
