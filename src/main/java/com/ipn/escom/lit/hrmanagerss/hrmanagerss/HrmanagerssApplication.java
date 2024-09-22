package com.ipn.escom.lit.hrmanagerss.hrmanagerss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class HrmanagerssApplication {

  public static void main(String[] args) {
    SpringApplication.run(HrmanagerssApplication.class, args);
  }

  @Bean
  public CorsFilter corsFilter() {

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(false);
    config.addAllowedOrigin("*");
    config.addAllowedHeader("*");
    config.addAllowedMethod("*");
    config.addAllowedMethod("GET");
    config.addAllowedMethod("PUT");
    config.addAllowedMethod("POST");
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
  }
}
