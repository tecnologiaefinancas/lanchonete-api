package com.fiap.tech.fiap_tech_challenge.categories.infra;


import com.fiap.tech.fiap_tech_challenge.product.infra.ProductJpaEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="categories")
@Data
public class CategoryJpaEntity {

    @Id
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "category")
    private List<ProductJpaEntity> producties;

}
