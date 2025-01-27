package io.github.lucasfreitasrocha.simplebank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;


@SpringBootApplication
@EnableFeignClients
@EntityScan(basePackages = "io.github.lucasfreitasrocha.simplebank")
@ComponentScan(
        basePackages = "io.github.lucasfreitasrocha.simplebank",
        excludeFilters = {
                @ComponentScan.Filter (type = FilterType.ASSIGNABLE_TYPE, value = ProcessingPaymentWorker.class),
                @ComponentScan.Filter(
                        type = FilterType.REGEX,
                        pattern = "io\\.github\\.lucasfreitasrocha\\.simplebank\\.entrryPoint\\.worker\\..*")
        }
)
public class SimpleBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleBankApplication.class, args);
    }

}
