package com.microservices.product.service;

import com.microservices.product.entity.Product;
import com.microservices.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public Product create(Product product) {
        return repo.save(product);
    }

    public List<Product> getAll() {
        return repo.findAll();
    }

    public Product getById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public Product update(Long id, Product updated) {
        Product product = repo.findById(id).orElseThrow();

        product.setName(updated.getName());
        product.setDescription(updated.getDescription());
        product.setPrice(updated.getPrice());

        return repo.save(product);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
