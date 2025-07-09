package likelion.shopping_mall.repository;

import likelion.shopping_mall.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
