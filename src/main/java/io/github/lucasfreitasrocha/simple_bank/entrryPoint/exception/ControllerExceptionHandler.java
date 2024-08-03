package io.github.lucasfreitasrocha.simple_bank.entrryPoint.exception;

import io.github.lucasfreitasrocha.simple_bank.core.exception.ErrorModel;
import io.github.lucasfreitasrocha.simple_bank.core.exception.HandlerErrorModel;
import io.github.lucasfreitasrocha.simple_bank.core.exception.HandlerException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler  {

    @ExceptionHandler(HandlerException.class)
    public ResponseEntity<HandlerErrorModel> handler(HandlerException e, HttpServletRequest req) {
        e.getHandlerErrorModel().setPath(req.getRequestURI());
        return ResponseEntity.status(e.getHandlerErrorModel().getStatus()).body(e.getHandlerErrorModel());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HandlerErrorModel> othersExpections(Exception e, HttpServletRequest req){
        HandlerErrorModel model = new HandlerErrorModel();
        model.getErrors().add(new ErrorModel(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage()));
        model.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        model.setPath(req.getRequestURI());
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(model);
    }
}