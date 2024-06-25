package com.lanchonete.tech.core.application.category.retrieve.list;


import com.lanchonete.tech.core.domain.category.Category;
import com.lanchonete.tech.core.domain.category.CategoryID;

import java.time.Instant;

public record CategoryListOutput(
        CategoryID id,
        String name,
        String description,
        boolean isActive,
        Instant createdAt,
        Instant deletedAt
) {

    public static CategoryListOutput from(final Category anCategory) {
        return new CategoryListOutput(anCategory.getId(),
                anCategory.getName(),
                anCategory.getDescription(),
                anCategory.isActive(),
                anCategory.getCreatedAt(),
                anCategory.getDeletedAt()
        );
    }
}
