package com.geekbrains.marketdemoa.repositories.specifications;

import com.geekbrains.marketdemoa.entites.Item;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ItemSpecifications {
    public static Specification<Item> priceGEThan(int value) {
        return (Specification<Item>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), value);
    }

    public static Specification<Item> priceLEThan(int value) {
        return (Specification<Item>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), value);
    }

    public static Specification<Item> categoryIdEquals(Long catId) {
//        return (Specification<Item>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("category"), catId); // тоже работает
        return (Specification<Item>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("category").get("id"), catId);
    }
}
