package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        if (product.getProductId() == null) {
            product.setProductId(UUID.randomUUID().toString());
        }
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    // FITUR BARU

    // Mencari produk berdasarkan ID
    public Product findById(String id) {
        for (Product product : productData) {
            if (product.getProductId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    // Edit produk
    public Product edit(Product product) {
        for (int i = 0; i < productData.size(); i++) {
            Product product1 = productData.get(i);
            if (product1.getProductId().equals(product.getProductId())) {
                product1.setProductName(product.getProductName());
                product1.setProductQuantity(product.getProductQuantity());
                return product1;
            }
        }
        return null;
    }
}
