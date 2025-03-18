package gr.codehub.eshopdemo.application.service;

import gr.codehub.eshopdemo.application.dto.ProductDTO;
import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Long id);
    ProductDTO saveProduct(ProductDTO productDTO);
    void deleteProduct(Long id);
    ProductDTO updateProduct(Long id, ProductDTO updatedProduct);
    List<ProductDTO> getProductsByPriceRange(double minPrice, double maxPrice);
} 