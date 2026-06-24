package com.ecommerce.feature.product;

import com.ecommerce.feature.category.CategoryRepository;
import com.ecommerce.feature.product.dto.CreateProductRequest;
import com.ecommerce.feature.product.dto.ProductResponse;
import com.ecommerce.utils.GenerateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductsServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    public Page<ProductResponse> findAll(int pageNumber, int pageSize) {
        Sort sortById = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);
        return productRepository.findAll(pageRequest).map(productMapper::toDto);
    }

    @Override
    public ProductResponse createNew(CreateProductRequest productRequest) {
        boolean existName = productRepository.existsByName(productRequest.name());
        if (existName)
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Product name has already been used"
            );

        var category = categoryRepository.findById(productRequest.categoryId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category ID is not found"
                ));

        var product = new Product();
        product.setCode(GenerateUtils.generateProductCode());
        product.setSlug(GenerateUtils.generateSlug(productRequest.name()));
        product.setName(productRequest.name());
        product.setDescription(productRequest.description());
        product.setThumbnail(productRequest.thumbnail());
        product.setUnitPrice(productRequest.unitPrice());
        product.setQuantity(productRequest.quantity());
        product.setIsAvailable(true);
        product.setIsDeleted(false);
        product.setCategory(category);

        product = productRepository.save(product);

        return productMapper.toDto(product);
    }
}
