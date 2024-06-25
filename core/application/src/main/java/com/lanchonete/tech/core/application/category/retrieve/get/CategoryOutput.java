package com.lanchonete.tech.core.application.category.retrieve.get;


import com.lanchonete.tech.core.domain.category.Category;
import com.lanchonete.tech.core.domain.category.CategoryID;

import java.time.Instant;

public record CategoryOutput(
        CategoryID id,
        String name,
        String description,
        boolean isActive,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {

    public static CategoryOutput from(final Category anCategory) {
        return new CategoryOutput(anCategory.getId(),
                anCategory.getName(),
                anCategory.getDescription(),
                anCategory.isActive(),
                anCategory.getCreatedAt(),
                anCategory.getUpdatedAt(),
                anCategory.getDeletedAt()
        );
    }
}
