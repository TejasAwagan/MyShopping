package com.devtejas.myshopping.service.product;

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

    List<Product> getProductByCategory(String category);

    List<Product> getProductByBrand(String brand);

    List<Product> getProductByCategoryAndBrand(String category, String brand);

    List<Product> getProductByName(String name);

    List<Product> getProductyBrandAndName(String brand, String name);

    Long coutProductByBrandAndName(String brand, String name);

}
