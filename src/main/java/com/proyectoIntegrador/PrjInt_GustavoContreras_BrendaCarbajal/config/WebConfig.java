package com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Permitir todas las rutas
                .allowedOrigins("http://localhost:63342")  // Cambia este por el origen de tu frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Métodos permitidos
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
