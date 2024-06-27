package com.lanchonete.tech.core.adapters.api;


import com.lanchonete.tech.core.adapters.category.models.CategoryListResponse;
import com.lanchonete.tech.core.adapters.category.models.CategoryResponse;
import com.lanchonete.tech.core.adapters.category.models.CreateCategoryRequest;
import com.lanchonete.tech.core.adapters.category.models.UpdateCategoryRequest;
import com.lanchonete.tech.core.domain.pagination.Pagination;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "categories")
public interface CategoryAPI {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> createCategory(@RequestBody CreateCategoryRequest input);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    Pagination<CategoryListResponse> listCategories(
            @RequestParam(name = "search", required = false, defaultValue = "") final String search,
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
            @RequestParam(name = "sort", required = false, defaultValue = "name") final String sort,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") final String direction
    );

    @GetMapping(
            value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    CategoryResponse getById(@PathVariable(name = "id") String id);

    @PutMapping(
            value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> updateCategoryById(
            @PathVariable(name = "id") String id,
            @RequestBody UpdateCategoryRequest input
    );

    @DeleteMapping(
            value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCategoryById(
            @PathVariable(name = "id") String id
    );
}
