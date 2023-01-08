package com.andrey.spring.validator;

public interface Validator<T> {
    ValidatorResult isValid(T object);
}
