package task1.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task1.task1.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
