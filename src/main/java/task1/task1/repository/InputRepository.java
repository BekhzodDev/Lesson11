package task1.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task1.task1.entity.Input;

public interface InputRepository extends JpaRepository<Input, Integer> {
    boolean existsBySupplierIdAndFactureNumber(Integer supplier_id, String factureNumber);
}
