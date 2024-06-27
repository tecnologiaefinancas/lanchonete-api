package com.lanchonete.tech.core.application.category.create;


import com.lanchonete.tech.core.application.UseCase;
import com.lanchonete.tech.core.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateCategoryUseCase extends UseCase<CreateCategoryCommand, Either<Notification, CreateCategoryOutput>> {

}
