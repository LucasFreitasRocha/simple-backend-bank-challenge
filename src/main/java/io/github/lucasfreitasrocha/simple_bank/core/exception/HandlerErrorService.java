package io.github.lucasfreitasrocha.simple_bank.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class HandlerErrorService {

    private HandlerErrorModel handlerErrorModel;
    public HandlerErrorService init(){
        handlerErrorModel = new HandlerErrorModel();
        return this;
    }

    public HandlerErrorService addError(String message){
        this.handlerErrorModel.getErrors().add(new ErrorModel("error:", message));
        return this;
    }
    public HandlerErrorService addFieldError(String field, String message){
        this.handlerErrorModel.getErrors().add(new ErrorModel(field, message));
        return this;
    }
    public HandlerErrorService addHttpStatus(HttpStatus status){
        this.handlerErrorModel.setStatus(status);
        return this;
    }
    public void handle(){
        if(!this.handlerErrorModel.getErrors().isEmpty()){
            throw new HandlerException(this.handlerErrorModel);
        }
    }
}