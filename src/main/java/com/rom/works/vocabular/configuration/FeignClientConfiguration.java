package com.rom.works.vocabular.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rom.works.vocabular.decoder.DictionaryRepositoryErrorDecoder;
import feign.Logger;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FeignClientConfiguration {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }

    @Bean
    public ErrorDecoder customerErrorDecoder() {
        return new DictionaryRepositoryErrorDecoder();
    }

    @Bean
    public DictionaryDefinitionClientFallback dictionaryDefinitionClientFallback(){
        return new DictionaryDefinitionClientFallback();
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
