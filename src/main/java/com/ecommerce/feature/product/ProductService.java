package com.ecommerce.feature.product;

import com.ecommerce.feature.product.dto.CreateProductRequest;
import com.ecommerce.feature.product.dto.ProductResponse;
import org.springframework.data.domain.Page;

public interface ProductService {
    Page<ProductResponse> findAll (int pageNumber, int pageSize);
    ProductResponse createNew(CreateProductRequest productRequest);
}
