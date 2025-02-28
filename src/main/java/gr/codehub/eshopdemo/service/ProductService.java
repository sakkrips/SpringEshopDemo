package gr.codehub.eshopdemo.service;

import gr.codehub.eshopdemo.model.Product;
import java.util.List;


public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product saveProduct(Product product);
    void deleteProduct(Long id);
    Product updateProduct(Long id, Product updatedProduct);
    List<Product> getProductsByPriceRange(double minPrice, double maxPrice);


}
