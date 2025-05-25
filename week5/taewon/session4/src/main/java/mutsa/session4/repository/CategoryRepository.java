package mutsa.session4.repository;

import java.util.Optional;
import mutsa.session4.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    public Optional<Category> findByName(String name);
}
