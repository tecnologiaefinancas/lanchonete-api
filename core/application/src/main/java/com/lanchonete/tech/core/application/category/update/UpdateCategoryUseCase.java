package com.lanchonete.tech.core.application.category.update;

import com.lanchonete.tech.core.application.UseCase;
import com.lanchonete.tech.core.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateCategoryUseCase extends UseCase<UpdateCategoryCommand, Either<Notification, UpdateCategoryOutput>> {
}
