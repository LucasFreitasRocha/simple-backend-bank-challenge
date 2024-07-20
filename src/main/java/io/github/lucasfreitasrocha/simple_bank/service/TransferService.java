package io.github.lucasfreitasrocha.simple_bank.service;

import io.github.lucasfreitasrocha.simple_bank.dto.TransferDto;
import io.github.lucasfreitasrocha.simple_bank.model.TransferModel;
import io.github.lucasfreitasrocha.simple_bank.model.UserModel;
import io.github.lucasfreitasrocha.simple_bank.repository.TransferRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransferService {

    private final UserService userService;
    private final TransferRepository transferRepository;

    public TransferDto transferValue(TransferDto transferDto) {
        UserModel payer = userService.findById(transferDto.getPayer());
        UserModel payee = userService.findById(transferDto.getPayee());
        TransferModel model = new TransferModel();
        model.setPayer(payer);
        model.setPayee(payee);
        model.setValue(transferDto.getValue());
        this.transferRepository.save(model);
        return transferDto;
    }
}
