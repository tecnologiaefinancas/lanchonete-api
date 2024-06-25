package com.lanchonete.tech.adapters.category.presenters;

import com.lanchonete.tech.adapters.category.models.CategoryListResponse;
import com.lanchonete.tech.adapters.category.models.CategoryResponse;
import com.lanchonete.tech.core.application.category.retrieve.get.CategoryOutput;
import com.lanchonete.tech.core.application.category.retrieve.list.CategoryListOutput;

public interface CategoryApiPresenter {

    static CategoryResponse present(final CategoryOutput output) {
        return new CategoryResponse(
                output.id().getValue(),
                output.name(),
                output.description(),
                output.isActive(),
                output.createdAt(),
                output.updatedAt(),
                output.deletedAt()
        );
    }

    static CategoryListResponse present(final CategoryListOutput output) {
        return new CategoryListResponse(
                output.id().getValue(),
                output.name(),
                output.description(),
                output.isActive(),
                output.createdAt(),
                output.deletedAt()
        );
    }
}
