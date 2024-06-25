package com.lanchonete.tech.core.application.utils;


import com.lanchonete.tech.core.domain.AggregateRoot;
import com.lanchonete.tech.core.domain.Identifier;
import com.lanchonete.tech.core.domain.exceptions.DomainException;
import com.lanchonete.tech.core.domain.exceptions.NotFoundException;

import java.util.function.Supplier;

public final class IDNotFoundUtils {
    public static Supplier<DomainException> notFound(final Identifier anId, Class<? extends AggregateRoot<?>> anAggregate) {
        return () -> NotFoundException.with(anAggregate, anId);
    }
}
