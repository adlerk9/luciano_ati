package edu.com.catolica.products.repository;

import edu.com.catolica.products.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByNameAndMaker(String name, String maker);}
