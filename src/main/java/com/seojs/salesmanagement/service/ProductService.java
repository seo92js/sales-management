package com.seojs.salesmanagement.service;

import com.seojs.salesmanagement.domain.category.Category;
import com.seojs.salesmanagement.domain.product.Product;
import com.seojs.salesmanagement.domain.product.ProductRepository;
import com.seojs.salesmanagement.domain.product.dto.ProductResponseDto;
import com.seojs.salesmanagement.domain.product.dto.ProductSaveDto;
import com.seojs.salesmanagement.domain.product.dto.ProductUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public Long save(ProductSaveDto productSaveDto) {
        Product product = Product.builder()
                .productName(productSaveDto.getProductName())
                .description(productSaveDto.getDescription())
                .price(productSaveDto.getPrice())
                .quantity(productSaveDto.getQuantity())
                .category(productSaveDto.getCategory())
                .build();

        return productRepository.save(product).getId();
    }

    @Transactional
    public ProductResponseDto findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow();

        return new ProductResponseDto(product);
    }

    @Transactional
    public List<ProductResponseDto> findByCategory(Category category) {
        return productRepository.findByCategory(category).stream()
                .map(ProductResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<ProductResponseDto> findAll() {
        return productRepository.findAll().stream()
                .map(ProductResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);
    }

    @Transactional
    public void update(Long id, ProductUpdateDto productUpdateDto) {
        Product product = productRepository.findById(id).orElseThrow();
        product.update(productUpdateDto.getQuantity(), productUpdateDto.getPrice());
    }
}
