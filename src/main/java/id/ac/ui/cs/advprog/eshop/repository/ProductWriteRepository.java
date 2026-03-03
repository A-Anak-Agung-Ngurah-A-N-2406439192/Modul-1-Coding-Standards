package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Product;

public interface ProductWriteRepository {
    Product create(Product product);
    Product edit(Product product);
    void delete(String id);
}