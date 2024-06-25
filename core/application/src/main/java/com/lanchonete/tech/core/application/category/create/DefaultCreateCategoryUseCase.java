package com.lanchonete.tech.core.application.category.create;


import com.lanchonete.tech.core.domain.category.Category;
import com.lanchonete.tech.core.domain.exceptions.NotificationException;
import com.lanchonete.tech.core.domain.validation.handler.Notification;
import com.lanchonete.tech.ports.category.CategoryGateway;

import java.util.Objects;


public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase{
    private final CategoryGateway categoryGateway;

    public DefaultCreateCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public CreateCategoryOutput execute(final CreateCategoryCommand aCommand) {
        final var aName = aCommand.name();
        final var aDescription = aCommand.description();
        final var isActive = aCommand.isActive();

        final var notification = Notification.create();

        final var aCategory = notification.validate(() -> Category.newCategory(aName, aDescription, isActive));

        if (notification.hasErrors()) {
            throw new NotificationException("Could not create Aggregate Genre", notification);
        }
        return CreateCategoryOutput.from(this.categoryGateway.create(aCategory));
    }
}
