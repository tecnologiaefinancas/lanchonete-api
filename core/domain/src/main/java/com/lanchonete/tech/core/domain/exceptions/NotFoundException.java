package com.lanchonete.tech.core.domain.exceptions;


import com.lanchonete.tech.core.domain.AggregateRoot;
import com.lanchonete.tech.core.domain.Identifier;
import com.lanchonete.tech.core.domain.validation.Error;

import java.util.Collections;
import java.util.List;

public class NotFoundException extends DomainException{

    protected NotFoundException(String aMessage, List<Error> anErrors) {
        super(aMessage, anErrors);
    }

    public static NotFoundException with(
           final Class<? extends AggregateRoot<?>> anAggregate,
           final Identifier id
    ) {
        final var anErrorMessage = "%s with ID %s was not found".formatted(anAggregate.getSimpleName(), id.getValue());
        return new NotFoundException(anErrorMessage, Collections.emptyList());
    }
}
