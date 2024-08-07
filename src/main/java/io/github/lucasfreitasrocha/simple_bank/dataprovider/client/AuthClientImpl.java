package io.github.lucasfreitasrocha.simple_bank.dataprovider.client;

import feign.FeignException;
import io.github.lucasfreitasrocha.simple_bank.core.gateway.AuthGateway;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AuthClientImpl implements AuthGateway {

    private final AuthClient authClient;

    @Override
    public boolean isAuthorized() {
        boolean response = false;
        try {
            response = authClient.getAuth().getData().isAuthorization();
        } catch (FeignException e) {
            if (e.status() == 403) {
               response = false;
            } else {
                log.error("ocorreu um erro ao chamar o autorizado" + e.getMessage());
                e.printStackTrace();
                throw e;
            }

        }
        return response;
    }
}
