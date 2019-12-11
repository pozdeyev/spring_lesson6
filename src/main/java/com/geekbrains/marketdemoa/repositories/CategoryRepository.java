package com.geekbrains.marketdemoa.repositories;

import com.geekbrains.marketdemoa.entites.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
