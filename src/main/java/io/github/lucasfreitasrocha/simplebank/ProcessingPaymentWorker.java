package io.github.lucasfreitasrocha.simplebank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@EntityScan(basePackages = "io.github.lucasfreitasrocha.simplebank")
@EnableFeignClients
@ComponentScan(
        basePackages = "io.github.lucasfreitasrocha.simplebank",
        excludeFilters = {
                @ComponentScan.Filter (type = FilterType.ASSIGNABLE_TYPE, value = SimpleBankApplication.class),
                @ComponentScan.Filter(
                        type = FilterType.REGEX,
                        pattern = "io\\.github\\.lucasfreitasrocha\\.simple_bank\\.entrryPoint\\.api\\..*")
        }
)
public class ProcessingPaymentWorker {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ProcessingPaymentWorker.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
