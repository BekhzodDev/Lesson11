package task1.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task1.task1.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
}
