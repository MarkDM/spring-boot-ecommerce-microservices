package com.mdm.product_service.controller;

import com.mdm.product_service.dto.ProductRequest;
import com.mdm.product_service.dto.ProductResponse;
import com.mdm.product_service.model.Product;
import com.mdm.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
        // Implementation for creating a product goes here
        var p = productService.createProduct(productRequest);
        return new ProductResponse(
                p.getId(),
                p.getName(),
                p.getDescription(),
                p.getPrice()
        );
    }


    @GetMapping("/paged")
    public Page<Product> getProductsPaged(Pageable pageable) {

//        if (pageable.getPageSize() > 100) {
//            pageable = pageable.withPage(100);
//        }
//
        return productService.getProducts(pageable);
    }

    @GetMapping
    public List<ProductResponse> getProducts(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        // Implementation for retrieving all products goes here
        var pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").ascending());

        return getProductsPaged(pageable).stream().map(
                p -> new ProductResponse(
                        p.getId(),
                        p.getName(),
                        p.getDescription(),
                        p.getPrice()
                )
        ).toList();

    }
}
