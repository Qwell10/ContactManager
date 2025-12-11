package com.contactManager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class WebServiceConfig {

    /**
     * Defines the Marshaller/Unmarshaller for JAXB2 - translate between java object and XML(language to store and transport data)
     */
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

        marshaller.setContextPath("com.contactManager.soap.gen");

        return marshaller;
    }
}