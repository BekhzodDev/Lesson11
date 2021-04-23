package task1.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task1.task1.entity.Category;
import task1.task1.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
//boolean existsByNameAndCategory(String name, Category category);
boolean existsByNameAndCategoryId(String name, Integer category_id);
}
