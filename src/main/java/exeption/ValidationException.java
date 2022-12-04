package exeption;

import validator.Error;

import java.util.List;

public class ValidationException extends RuntimeException{
    private final List<Error> errors;
    public List<Error> getErrors() {
        return errors;
    }
    public ValidationException(List<Error> errors) {
        this.errors = errors;
    }
}
