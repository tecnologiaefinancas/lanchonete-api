package com.lanchonete.tech.core.application.category.retrieve.list;


import com.lanchonete.tech.core.application.UseCase;
import com.lanchonete.tech.core.domain.pagination.Pagination;
import com.lanchonete.tech.core.domain.pagination.SearchQuery;

public abstract class ListCategoriesUseCase extends UseCase<SearchQuery, Pagination<CategoryListOutput>> {
}
