package mutsa.session4.repository;

import java.util.List;
import java.util.Optional;
import mutsa.session4.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follow,Long> {

    Optional<Follow> findByFromUserIdAndToUserId(Long fromUserId, Long toUserId);

    List<Follow> findByFromUserId(Long fromId);

    List<Follow> findByToUserId(Long toId);
}
