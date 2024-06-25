package com.lanchonete.tech.core.application.category.create;

import com.lanchonete.tech.core.domain.category.Category;

public record CreateCategoryOutput(
        String id
) {
    public static CreateCategoryOutput from (final Category aCategory){
        return new CreateCategoryOutput(aCategory.getId().getValue());
    }

    public static CreateCategoryOutput from (final String anId){
        return new CreateCategoryOutput(anId);
    }
}
