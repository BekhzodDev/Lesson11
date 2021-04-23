package task1.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task1.task1.entity.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
 boolean existsByName(String name);
}
