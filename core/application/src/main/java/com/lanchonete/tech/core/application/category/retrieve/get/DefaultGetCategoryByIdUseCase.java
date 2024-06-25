package com.lanchonete.tech.core.application.category.retrieve.get;


import com.lanchonete.tech.core.application.utils.IDNotFoundUtils;
import com.lanchonete.tech.core.domain.category.Category;
import com.lanchonete.tech.core.domain.category.CategoryID;
import com.lanchonete.tech.ports.category.CategoryGateway;

import java.util.Objects;

public class DefaultGetCategoryByIdUseCase extends GetCategoryByIdUseCase {
    private final CategoryGateway categoryGateway;

    public DefaultGetCategoryByIdUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public CategoryOutput execute(String anId) {
        final var aCategoryId = CategoryID.from(anId);
        return this.categoryGateway.findById(aCategoryId)
                .map(CategoryOutput::from)
                .orElseThrow(IDNotFoundUtils.notFound(aCategoryId, Category.class));
    }

}
