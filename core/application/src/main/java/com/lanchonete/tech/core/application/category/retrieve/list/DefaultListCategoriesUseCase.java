package com.lanchonete.tech.core.application.category.retrieve.list;


import com.lanchonete.tech.core.domain.category.CategoryGateway;
import com.lanchonete.tech.core.domain.pagination.Pagination;
import com.lanchonete.tech.core.domain.pagination.SearchQuery;

import java.util.Objects;

public class DefaultListCategoriesUseCase extends ListCategoriesUseCase{
    private final CategoryGateway categoryGateway;

    public DefaultListCategoriesUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Pagination<CategoryListOutput> execute(SearchQuery aSearchQuery) {
        return this.categoryGateway.findAll(aSearchQuery)
                .map(CategoryListOutput::from);
    }
}
