package com.fiap.tech.fiap_tech_challenge.product.infra.persistense;

import com.fiap.tech.fiap_tech_challenge.categories.infra.CategoryRepository;
import com.fiap.tech.fiap_tech_challenge.common.domain.pagination.Pagination;
import com.fiap.tech.fiap_tech_challenge.common.infra.utils.SpecificationUtils;
import com.fiap.tech.fiap_tech_challenge.product.domain.Product;
import com.fiap.tech.fiap_tech_challenge.product.domain.ProductGateway;
import com.fiap.tech.fiap_tech_challenge.product.domain.ProductID;
import com.fiap.tech.fiap_tech_challenge.product.domain.pagination.ProductSearchQuery;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Component

public class ProductPostgresGateway implements ProductGateway {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductPostgresGateway(final ProductRepository productRepository, final CategoryRepository categoryRepository) {
        this.productRepository = Objects.requireNonNull(productRepository);
        this.categoryRepository = Objects.requireNonNull(categoryRepository);
    }

    @Override
    public Product create(final Product aProduct) {
        return save(aProduct).toAggregate();
    }

    @Override
    public void deleteById(final ProductID anId) {
        final var aGenreId = anId.getValue();
        if(this.productRepository.existsById(aGenreId)) {
            productRepository.deleteById(aGenreId);
        }
    }

    @Override
    public List<ProductID> existsByIds(List<String> products) {
        return List.of(); //TODO - terminar
    }

    @Override
    public Optional<Product> findById(final ProductID anId) {
        return this.productRepository.findById(anId.getValue()).map(ProductJpaEntity::toAggregate);
    }

    @Override
    public Product update(Product aGenre) {
        return save(aGenre).toAggregate();

    }

    @Override
    public Pagination<Product> findAll(final ProductSearchQuery aQuery) {
        final var page = PageRequest.of(
                aQuery.page(),
                aQuery.perPage(),
                Sort.by(Sort.Direction.fromString(aQuery.direction()), aQuery.sort())
        );

        final var where = Optional.ofNullable(aQuery.categoryId())
                .filter(str -> !str.isBlank())
                .map(this::assembleSpecification)
                .orElse(null);

        final var results = this.productRepository.findAll(Specification.where(where), page);
        return new Pagination<>(
                results.getNumber(),
                results.getSize(),
                results.getTotalElements(),
                results.map(ProductJpaEntity::toAggregate).stream().toList()
        );
    }

    private Specification<ProductJpaEntity> assembleSpecification(final String category) {
        return SpecificationUtils.equal("categoryId", category);
    }

    private ProductJpaEntity save(Product aProduct) {
        return this.productRepository.save(ProductJpaEntity.from(aProduct));
    }
}
