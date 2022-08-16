package com.hos05020.shopping.repository;

import com.hos05020.shopping.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long>,ProductRepositoryCustom {
}
