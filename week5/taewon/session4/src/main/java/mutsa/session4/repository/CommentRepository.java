package mutsa.session4.repository;

import java.util.List;
import java.util.Optional;
import mutsa.session4.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    public List<Comment> findByPostId(Long postId);

    public List<Comment> findByParentId(Long parendId);
}
