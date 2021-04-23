package task1.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task1.task1.entity.Output;

public interface OutputRepository extends JpaRepository<Output, Integer> {
    boolean existsByClientIdAndFactureNumber(Integer client_id, String factureNumber);

}
