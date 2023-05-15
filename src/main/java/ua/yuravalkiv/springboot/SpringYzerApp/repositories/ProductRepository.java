package ua.yuravalkiv.springboot.SpringYzerApp.repositories;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.yuravalkiv.springboot.SpringYzerApp.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

}