package io.github.lucasfreitasrocha.simple_bank.exception;

public class HandlerException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private  HandlerErrorModel handlerErrorModel;

    public HandlerException(HandlerErrorModel model){
        super("Há erros para serem tratadas");
        this.handlerErrorModel = model;
    }
    public HandlerException(String message, Throwable cause) {
        super(message, cause);
    }

    public HandlerErrorModel getHandlerErrorModel() {
        return handlerErrorModel;
    }
}