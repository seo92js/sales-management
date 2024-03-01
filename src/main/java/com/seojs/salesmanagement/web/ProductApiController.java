package com.seojs.salesmanagement.web;

import com.seojs.salesmanagement.domain.product.dto.ProductResponseDto;
import com.seojs.salesmanagement.domain.product.dto.ProductSaveDto;
import com.seojs.salesmanagement.domain.product.dto.ProductUpdateDto;
import com.seojs.salesmanagement.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductApiController {
    private final ProductService productService;

    @PostMapping("/api/v1/product")
    public Long save(@RequestBody ProductSaveDto productSaveDto) {
        return productService.save(productSaveDto);
    }

    @GetMapping("/api/v1/product/{id}")
    public ProductResponseDto findById(@PathVariable(name = "id") Long id) {
        return productService.findById(id);
    }

    @GetMapping("/api/v1/product/category/{categoryName}")
    public List<ProductResponseDto> findByCategoryName(@PathVariable(name = "categoryName") String categoryName) {
        return productService.findByCategoryName(categoryName);
    }

    @GetMapping("/api/v1/products")
    public List<ProductResponseDto> findAll() {
        return productService.findAll();
    }

    @DeleteMapping("/api/v1/product/{id}")
    public void delete(@PathVariable(name="id") Long id) {
        productService.delete(id);
    }

    @PatchMapping("/api/v1/product/{id}")
    public void update(@PathVariable(name = "id") Long id, @RequestBody ProductUpdateDto productUpdateDto) {
        productService.update(id, productUpdateDto);
    }
}
