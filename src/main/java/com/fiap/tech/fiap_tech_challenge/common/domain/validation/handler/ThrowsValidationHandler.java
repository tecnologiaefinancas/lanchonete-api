package com.fiap.tech.fiap_tech_challenge.common.domain.validation.handler;


import com.fiap.tech.fiap_tech_challenge.common.domain.exceptions.DomainException;
import com.fiap.tech.fiap_tech_challenge.common.domain.validation.Error;
import com.fiap.tech.fiap_tech_challenge.common.domain.validation.ValidationHandler;

import java.util.List;

public class ThrowsValidationHandler implements ValidationHandler {

    @Override
    public ValidationHandler append(Error anError) {
        throw DomainException.with(anError);
    }

    @Override
    public ValidationHandler append(ValidationHandler aHandler) {
        throw DomainException.with(aHandler.getErrors());

    }

    @Override
    public <T> T validate(final Validation<T> aValidation) {
        try {
            return aValidation.validate();
        } catch (final Exception ex) {
            throw DomainException.with(new Error(ex.getMessage()));
        }
    }

    @Override
    public List<Error> getErrors() {
        return List.of();
    }
}
