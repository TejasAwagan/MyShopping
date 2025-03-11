package com.devtejas.myshopping.controller;

import com.devtejas.myshopping.dto.ProductDto;
import com.devtejas.myshopping.exception.ResourceNotFoundException;
import com.devtejas.myshopping.models.Product;
import com.devtejas.myshopping.request.AddProductRequest;
import com.devtejas.myshopping.request.ProductUpdateRequest;
import com.devtejas.myshopping.response.ApiResponse;
import com.devtejas.myshopping.service.product.IProductService;
import com.devtejas.myshopping.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationPid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
@RequestMapping("${api.prefix}/products")
public class ProductController {

    @Autowired
    IProductService productService;


    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
        return  ResponseEntity.ok(new ApiResponse("success", convertedProducts));
    }

    @GetMapping("product/{productId}/product")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId){
        try {
            Product product = productService.getProductById(productId);
            ProductDto productDto = productService.convertToDto(product);
            return ResponseEntity.ok(new ApiResponse("Success", productDto));
        }
        catch(ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct (@RequestBody AddProductRequest product){
        try{
            Product newProduct = productService.addProduct(product);
            ProductDto productDto = productService.convertToDto(newProduct);
            return ResponseEntity.ok(new ApiResponse("Product Added Successfuly !", productDto));
        }
        catch(Exception e)
        {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("product/{productId}/update")
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody ProductUpdateRequest request, @PathVariable Long productId){
        try {
            Product theProduct = productService.updateProduct(request, productId);
            ProductDto productDto = productService.convertToDto(theProduct);
            return ResponseEntity.ok(new ApiResponse("Update product success!", productDto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/product/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) {
        try {
            productService.deleteProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Delete product success!", productId));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/products/by/brand-and-name")
    public ResponseEntity<ApiResponse> getProductByBrandAndName(@RequestParam String brandName, @RequestParam String productName) {
        try {
            List<Product> products = productService.getProductsByBrandAndName(brandName, productName);
            if (products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No products found ", null));
            }
            List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
            return  ResponseEntity.ok(new ApiResponse("success", convertedProducts));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/products/by/category-and-brand")
    public ResponseEntity<ApiResponse> getProductByCategoryAndBrand(@RequestParam String category, @RequestParam String brandName){
        try{
            List<Product> products = productService.getProductByCategoryAndBrand(category, brandName);
            if(products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No products found" , null));
            }

            List<ProductDto> convertToDto = productService.getConvertedProducts(products);
            return ResponseEntity.ok(new ApiResponse("success", convertToDto));
        }
        catch(Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", e.getMessage()));
        }
    }

    @GetMapping("/product/{productName}/products")
    public ResponseEntity<ApiResponse> getProductByName(@RequestParam String productName){
        try {
            List<Product> products = productService.getProductByName(productName);

            if(productName.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Product Not Found", null));
            }

            List<ProductDto> convertedProduct = productService.getConvertedProducts(products);
            return ResponseEntity.ok(new ApiResponse("Product Found Successfully !",convertedProduct));
        }
        catch(ResourceNotFoundException e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", e.getMessage()));
        }
    }

    @GetMapping("/product/by-brand")
    public ResponseEntity<ApiResponse> findProductByBrand(@RequestParam String brand) {
        try {
            List<Product> products = productService.getProductsByBrand(brand);
            if (products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No products found ", null));
            }
            List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
            return  ResponseEntity.ok(new ApiResponse("success", convertedProducts));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/product/{category}/all/products")
    public ResponseEntity<ApiResponse> findProductByCategory(@PathVariable String category) {
        try {
            List<Product> products = productService.getProductsByCategory(category);
            if (products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No products found ", null));
            }
            List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
            return  ResponseEntity.ok(new ApiResponse("success", convertedProducts));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/product/count/by-brand/and-name")
    public ResponseEntity<ApiResponse> countProductsByBrandAndName(@RequestParam String brand, @RequestParam String name) {
        try {
            var productCount = productService.countProductsByBrandAndName(brand, name);
            return ResponseEntity.ok(new ApiResponse("Product count!", productCount));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(e.getMessage(), null));
        }
    }


}





































