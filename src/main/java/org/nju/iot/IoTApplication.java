package org.nju.iot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ConfigurationPropertiesScan
@ComponentScan(basePackages = "org.nju.iot.*")
@EnableJpaRepositories(basePackages = "org.nju.iot.dao")
@EntityScan("org.nju.iot.*")
@SpringBootApplication
public class IoTApplication {

    public static void main(String[] args) {
        SpringApplication.run(IoTApplication.class, args);
    }

}
