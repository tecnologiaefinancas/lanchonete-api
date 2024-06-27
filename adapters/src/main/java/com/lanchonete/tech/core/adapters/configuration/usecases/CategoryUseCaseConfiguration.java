package com.lanchonete.tech.core.adapters.configuration.usecases;


import com.lanchonete.tech.core.application.category.create.CreateCategoryUseCase;
import com.lanchonete.tech.core.application.category.create.DefaultCreateCategoryUseCase;
import com.lanchonete.tech.core.application.category.delete.DefaultDeleteCategoryUseCase;
import com.lanchonete.tech.core.application.category.delete.DeleteCategoryUseCase;
import com.lanchonete.tech.core.application.category.retrieve.get.DefaultGetCategoryByIdUseCase;
import com.lanchonete.tech.core.application.category.retrieve.get.GetCategoryByIdUseCase;
import com.lanchonete.tech.core.application.category.retrieve.list.DefaultListCategoriesUseCase;
import com.lanchonete.tech.core.application.category.retrieve.list.ListCategoriesUseCase;
import com.lanchonete.tech.core.application.category.update.DefaultUpdateCategoryUseCase;
import com.lanchonete.tech.core.application.category.update.UpdateCategoryUseCase;
import com.lanchonete.tech.core.domain.category.CategoryGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryUseCaseConfiguration {

    private final CategoryGateway categoryGateway;

    public CategoryUseCaseConfiguration(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    @Bean
    public CreateCategoryUseCase createCategoryUseCase() {
        return new DefaultCreateCategoryUseCase(categoryGateway);
    }

    @Bean
    public UpdateCategoryUseCase updateCategoryUseCase() {
        return new DefaultUpdateCategoryUseCase(categoryGateway);
    }

    @Bean
    public DeleteCategoryUseCase deleteCategoryUseCase() {
        return new DefaultDeleteCategoryUseCase(categoryGateway);
    }

    @Bean
    public GetCategoryByIdUseCase getCategoryByIdUseCase() {
        return new DefaultGetCategoryByIdUseCase(categoryGateway);
    }

    @Bean
    public ListCategoriesUseCase listCategoriesUseCase() {
        return new DefaultListCategoriesUseCase(categoryGateway);
    }
}
