package io.github.lucasfreitasrocha.simple_bank.dataprovider.client;


import io.github.lucasfreitasrocha.simple_bank.dataprovider.client.dto.out.ResponseAuthDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "authClient", url = "${services.auth.url}")
public interface AuthClient {

    @GetMapping("/api/v2/authorize")
    public ResponseAuthDto getAuth();

}
