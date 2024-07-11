package com.usdev;

import com.usdev.application.ports.api.PricesAPIService;
import com.usdev.application.ports.spi.PricePersistance;
import com.usdev.application.service.PriceService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.usdev"})
@EnableJpaRepositories(basePackages = {"com.usdev.infrastructure.postgres.repository"})
@EntityScan(basePackages = {"com.usdev.infrastructure.postgres.entity"})
@EnableTransactionManagement
public class DemoHexagonalApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoHexagonalApplication.class, args);
    }

    @Bean
    PricesAPIService pricesAPIService(PricePersistance pricePersistance) {
        return new PriceService(pricePersistance);
    }

}
