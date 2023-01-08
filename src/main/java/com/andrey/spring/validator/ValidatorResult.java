package com.andrey.spring.validator;

import java.util.ArrayList;
import java.util.List;

public class ValidatorResult {

    private List<Error> errors = new ArrayList<>();
    public List<Error> getErrors() {
        return errors;
    }



    public void add(Error error){
        this.errors.add(error);
    }

    public boolean isValid(){
        return errors.isEmpty();
    }
}
