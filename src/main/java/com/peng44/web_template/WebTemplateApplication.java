package com.peng44.web_template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author Nile
 */
@SpringBootApplication
@EnableCaching
public class WebTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebTemplateApplication.class, args);
    }

}
