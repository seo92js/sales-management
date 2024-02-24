package com.seojs.salesmanagement.service;

import com.seojs.salesmanagement.domain.category.Category;
import com.seojs.salesmanagement.domain.product.Product;
import com.seojs.salesmanagement.domain.product.ProductRepository;
import com.seojs.salesmanagement.domain.product.dto.ProductResponseDto;
import com.seojs.salesmanagement.domain.product.dto.ProductSaveDto;
import com.seojs.salesmanagement.domain.product.dto.ProductUpdateDto;
import com.seojs.salesmanagement.exception.ProductDuplicateEx;
import com.seojs.salesmanagement.exception.ProductNotFoundEx;
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
        checkDuplicateProduct(productSaveDto.getProductName());

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
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundEx("제품이 없습니다. id = " + id));

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
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundEx("제품이 없습니다. id = " + id));
        productRepository.delete(product);
    }

    @Transactional
    public void update(Long id, ProductUpdateDto productUpdateDto) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundEx("제품이 없습니다. id = " + id));
        product.update(productUpdateDto.getQuantity(), productUpdateDto.getPrice());
    }

    private void checkDuplicateProduct(String productName) {
        productRepository.findByProductName(productName).ifPresent(
                existingProduct -> {
                    throw new ProductDuplicateEx("중복된 제품이 있습니다. productName = " + productName);
                }
        );
    }
}
