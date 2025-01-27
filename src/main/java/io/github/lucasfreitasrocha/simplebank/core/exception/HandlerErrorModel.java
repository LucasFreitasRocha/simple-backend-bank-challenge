package io.github.lucasfreitasrocha.simplebank.core.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HandlerErrorModel {
    private List<ErrorModel> errors;
    private HttpStatus status;
    private LocalDateTime date;
    private String path;

    public HandlerErrorModel() {
        this.status = HttpStatus.BAD_REQUEST;
        this.date = LocalDateTime.now();
        this.errors = new ArrayList<>();
    }

    public HandlerErrorModel(List<ErrorModel> errors, HttpStatus status, LocalDateTime timestamp) {
        this.errors = errors;
        this.status = status;
        this.date = timestamp;
    }

    public List<ErrorModel> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorModel> errors) {
        this.errors = errors;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}