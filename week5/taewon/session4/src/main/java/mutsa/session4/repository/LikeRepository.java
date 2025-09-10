package mutsa.session4.repository;

import java.util.List;
import mutsa.session4.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {

    List<Like> findByPostId(Long id);

    List<Like> findByUserId(Long id);

    Like findByUserIdAndPostId(Long userId, Long postId);
}
