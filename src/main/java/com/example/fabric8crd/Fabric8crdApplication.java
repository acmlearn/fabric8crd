package com.example.fabric8crd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication(exclude={ErrorMvcAutoConfiguration.class, RepositoryRestMvcAutoConfiguration.class})
public class Fabric8crdApplication {

    public static void main(String[] args) {
        SpringApplication.run(Fabric8crdApplication.class, args);
    }

}
