package id.ac.ui.cs.advprog.eshop.service;
import id.ac.ui.cs.advprog.eshop.model.Product;

public interface ProductWriteService {
    Product create(Product product);
    Product edit(Product product);
    void delete(String productId);
}