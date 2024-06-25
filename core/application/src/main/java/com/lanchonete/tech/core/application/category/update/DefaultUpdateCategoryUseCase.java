package com.lanchonete.tech.core.application.category.update;


import com.lanchonete.tech.core.application.utils.IDNotFoundUtils;
import com.lanchonete.tech.core.domain.category.Category;
import com.lanchonete.tech.core.domain.category.CategoryID;
import com.lanchonete.tech.core.domain.exceptions.NotificationException;
import com.lanchonete.tech.core.domain.validation.handler.Notification;
import com.lanchonete.tech.ports.category.CategoryGateway;

import java.util.Objects;

public class DefaultUpdateCategoryUseCase extends UpdateCategoryUseCase{

    private final CategoryGateway categoryGateway;

    public DefaultUpdateCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public UpdateCategoryOutput execute(final UpdateCategoryCommand aCommand) {
        final var anId = CategoryID.from(aCommand.id());
        final var aName = aCommand.name();
        final var aDescription = aCommand.description();
        final var isActive = aCommand.isActive();

        final var aCategory = this.categoryGateway.findById(anId).orElseThrow(IDNotFoundUtils.notFound(anId, Category.class));

        final var notification = Notification.create();

        notification.validate(() -> aCategory.update(aName, aDescription, isActive));

        if (notification.hasErrors()) {
            throw new NotificationException(
                    "Could not update Aggregate Genre %s".formatted(aCommand.id()), notification);
        }
        return UpdateCategoryOutput.from(this.categoryGateway.update(aCategory));

    }

}
