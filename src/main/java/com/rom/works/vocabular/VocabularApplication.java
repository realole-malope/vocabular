package com.rom.works.vocabular;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.rom.works.vocabular"})
@EnableFeignClients
public class VocabularApplication {

    public static void main(String[] args) {
        SpringApplication.run(VocabularApplication.class, args);
    }
}
