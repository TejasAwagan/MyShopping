package com.devtejas.myshopping.service.product;

import com.devtejas.myshopping.dto.ProductDto;
import com.devtejas.myshopping.models.Product;
import com.devtejas.myshopping.request.AddProductRequest;
import com.devtejas.myshopping.request.ProductUpdateRequest;
import org.springframework.context.event.EventListenerMethodProcessor;

import java.util.List;

public interface IProductService {
    Product addProduct(AddProductRequest product);

    Product getProductById(Long id);

    void deleteProductById(Long id);

    Product updateProduct(ProductUpdateRequest product, Long productId);

    List<Product> getAllProducts();

    List<Product> getProductsByCategory(String category);

    List<Product> getProductsByBrand(String brand);

    List<Product> getProductByCategoryAndBrand(String category, String brand);

    List<Product> getProductByName(String name);

    List<Product> getProductsByBrandAndName(String brand, String name);

    Long countProductsByBrandAndName(String brand, String name);

    List<ProductDto> getConvertedProducts(List<Product> products);

    ProductDto convertToDto(Product product);


}
